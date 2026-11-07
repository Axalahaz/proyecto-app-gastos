package com.finanzas.app.identity.infrastructure.security.jwt;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.finanzas.app.identity.infrastructure.security.adapter.UserDetailsAdapter;
import com.finanzas.app.identity.infrastructure.security.adapter.UserDetailsServiceImpl;
import com.finanzas.app.identity.infrastructure.security.exception.TokenExpiredException;
import com.finanzas.app.identity.infrastructure.security.exception.TokenInvalidoException;
import com.finanzas.app.identity.infrastructure.security.exception.handler.CustomAuthenticationEntryPoint;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

/**
 * Filtro de autenticación basado en tokens JWT.
 *
 * Intercepta las solicitudes HTTP para identificar y autenticar
 * al usuario mediante el token de acceso enviado en el encabezado
 * Authorization.
 *
 * Verifica la validez del token, comprueba que el usuario asociado
 * continúe habilitado y que no existan modificaciones posteriores
 * a su emisión que invaliden la sesión.
 *
 * Si la autenticación es válida, registra al usuario en el
 * {@link SecurityContextHolder} para que Spring Security pueda
 * reconocer su identidad durante el procesamiento de la solicitud.
 *
 * Extiende {@link OncePerRequestFilter} para garantizar una única
 * ejecución del filtro por solicitud dentro de su ciclo de ejecución.
 *
 * @see JwtTokenService
 * @see UserDetailsServiceImpl
 * @see CustomAuthenticationEntryPoint
 */
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenService jwtTokenService;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    private final UserDetailsServiceImpl userDetailsServiceImpl;

    /**
     * Procesa la solicitud HTTP para determinar si contiene un token
     * JWT y, en caso afirmativo, autenticar al usuario asociado.
     *
     * Si no existe un encabezado Authorization con el esquema Bearer,
     * continúa con la cadena de filtros sin establecer una autenticación.
     *
     * Cuando existe un token, valida su contenido, recupera al usuario
     * y comprueba que no haya cambios en sus credenciales o estado
     * que invaliden la sesión.
     *
     * Si la autenticación es correcta, registra la identidad del usuario
     * en el contexto de seguridad de Spring.
     *
     * Los errores de autenticación se delegan al
     * {@link CustomAuthenticationEntryPoint}.
     *
     * @param request solicitud HTTP recibida.
     * @param response respuesta HTTP que podrá utilizarse para comunicar
     *                  errores de autenticación.
     * @param filterChain cadena de filtros que continúa procesando
     *                    la solicitud.
     * @throws ServletException si ocurre un error durante el procesamiento
     *                           de la solicitud.
     * @throws IOException si ocurre un error de entrada o salida.
     */
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

    		// Obtener el encabezado Authorization.
			String authHeader = request.getHeader("Authorization");

			// Si no hay Bearer token, continúa sin autenticar.
	        // Spring Security decidirá si permite o rechaza el acceso.
			if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			    filterChain.doFilter(request, response);
			    return;
			}

			// Extraer el token del esquema Bearer.
			String token = authHeader.substring(7);

			try {
				// Validar el token y obtener sus claims.
				Claims claims = jwtTokenService.obtenerClaimsTokenSeguro(token);
				
				// Extraer id
				Long usuarioId = Long.valueOf(claims.getSubject());
				// Extraer fecha de emisión
				Date issuedAt = claims.getIssuedAt();	
				
				if (issuedAt == null) {
					throw new BadCredentialsException("Token sin fecha válida");
				}

				// Busca usuario
				UserDetails userDetails = userDetailsServiceImpl.loadUserById(usuarioId);
				
				if (!(userDetails instanceof UserDetailsAdapter adapter)) {
				    throw new BadCredentialsException(
				        "No se pudo identificar al usuario"
				    );
				}

				// Verificar si hubo cambios que invaliden el token. (invalidación de sesión)
				LocalDateTime passwordUpdatedAt = adapter.getFechaCambioPassword();
		        LocalDateTime estadoUpdatedAt = adapter.getFechaCambioEstado();
	
		        // Invalidar tokens emitidos antes del último cambio de contraseña.
		        if (passwordUpdatedAt != null &&
		                issuedAt.toInstant().isBefore(
		                        passwordUpdatedAt.atZone(ZoneOffset.UTC).toInstant()
		                )) {
	
		            throw new TokenInvalidoException(
		                    "La contraseña fue modificada. Inicie sesión nuevamente"
		            );
		        }
		        
		        // Impedir la autenticación de usuarios desactivados.
		        if (!adapter.isEnabled()) {
		        	throw new DisabledException("El usuario está desactivado");
		        }
		        
		        if (estadoUpdatedAt != null &&
			            issuedAt.toInstant().isBefore(
			                estadoUpdatedAt.atZone(ZoneOffset.UTC).toInstant()
			            )) {
	            
		            throw new TokenInvalidoException(
		                "El estado del usuario cambió. Inicie sesión nuevamente"
		            );
		        }
				
				// Registrar la autenticación en el contexto de Spring Security
	            // únicamente si todavía no existe una autenticación establecida.
				if (SecurityContextHolder.getContext().getAuthentication() == null) {
	
					Authentication authentication =
				            new UsernamePasswordAuthenticationToken(
				            		userDetails,
				                    null,
				                    userDetails.getAuthorities()
				            );
	
				    SecurityContextHolder.getContext().setAuthentication(authentication);
				}
	
			}  catch (ExpiredJwtException ex) {

			    SecurityContextHolder.clearContext();
			    customAuthenticationEntryPoint.commence(
			        request,
			        response,
			        new TokenExpiredException("El token ha expirado")
			    );
			    
			    return;

			} catch (JwtException | IllegalArgumentException ex) {

			    SecurityContextHolder.clearContext();
			    customAuthenticationEntryPoint.commence(
			        request,
			        response,
			        new BadCredentialsException("Token inválido")
			    );
			    
			    return;
			    
			} catch (AuthenticationException  ex) {
				
			    SecurityContextHolder.clearContext();

			    // Delegar el tratamiento del error al componente encargado
	            // de gestionar los errores de autenticación.
			    customAuthenticationEntryPoint.commence(
				    request, 
				    response, 
				    ex
			    );
			    
			    return;
			}
			
			// Continuar con la cadena de filtros.
	        filterChain.doFilter(request, response);
    }
}