package com.finanzas.app.shared.infrastructure.security.jwt;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.finanzas.app.shared.infrastructure.security.adapter.UserDetailsAdapter;
import com.finanzas.app.shared.infrastructure.security.exception.TokenExpiredException;
import com.finanzas.app.shared.infrastructure.security.exception.TokenInvalidoException;
import com.finanzas.app.shared.infrastructure.security.exception.handler.CustomAuthenticationEntryPoint;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

/*
 * Intercepta Request
 * */
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenService jwtTokenService;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    
    // Interfaz → Spring inyecta implementación de UserDetails
    private final UserDetailsService userDetailsService;

    /**
     * Este filtro se ejecuta UNA VEZ por request
     * antes de llegar al controller
     */
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

			final String authHeader = request.getHeader("Authorization");

			// si no hay token → continúa sin autenticar (Spring Security decide si permite o no el acceso)
			if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			    filterChain.doFilter(request, response);
			    return;
			}

			// sacar "Bearer "
			String token = authHeader.substring(7);

			try {
				// Validar token
				Claims claims = jwtTokenService.obtenerClaimsTokenSeguro(token);
				
				// Extraer id
				String usuarioId = claims.getSubject();
				Date issuedAt = claims.getIssuedAt();
				
				if (issuedAt == null) {
					throw new BadCredentialsException("Token sin fecha válida");
				}

				// cargar usuario
				UserDetails userDetails = userDetailsService.loadUserByUsername(usuarioId);
				
				// Verificación de cambio de password (invalidación de sesión)
				if (userDetails instanceof UserDetailsAdapter adapter) {

	                LocalDateTime passwordUpdatedAt = adapter.getFechaCambioPassword();
	                LocalDateTime estadoUpdatedAt = adapter.getFechaCambioEstado();

	                if (passwordUpdatedAt != null &&
	                        issuedAt.toInstant().isBefore(
	                                passwordUpdatedAt.atZone(ZoneId.systemDefault()).toInstant()
	                        )) {

	                    throw new TokenInvalidoException(
	                            "La contraseña fue modificada. Inicie sesión nuevamente"
	                    );
	                }
	                
	                if (!adapter.isEnabled()) {
	                	throw new DisabledException("El usuario está desactivado");
	                }
	                
	                if (estadoUpdatedAt != null &&
	                	    issuedAt.toInstant().isBefore(
	                	        estadoUpdatedAt.atZone(ZoneId.systemDefault()).toInstant()
	                	    )) {

	                	    throw new TokenInvalidoException(
	                	        "El estado del usuario cambió. Inicie sesión nuevamente"
	                	    );
	                	}
	            }
				
				// setear autenticación si no existe
				if (SecurityContextHolder.getContext().getAuthentication() == null) {
	
				    UsernamePasswordAuthenticationToken auth =
				            new UsernamePasswordAuthenticationToken(
				            		userDetails,
				                    null,
				                    userDetails.getAuthorities()
				            );
	
				    SecurityContextHolder.getContext().setAuthentication(auth);
				}
				
				// continuar
				filterChain.doFilter(request, response);
	
			}  catch (io.jsonwebtoken.ExpiredJwtException ex) {

			    SecurityContextHolder.clearContext();
			    customAuthenticationEntryPoint.commence(
			        request,
			        response,
			        new TokenExpiredException("El token ha expirado")
			    );

			} catch (IllegalArgumentException ex) {

			    SecurityContextHolder.clearContext();
			    customAuthenticationEntryPoint.commence(
			        request,
			        response,
			        new BadCredentialsException("Token vacío o inválido")
			    );
			    
			} catch (AuthenticationException  ex) {
			    SecurityContextHolder.clearContext();

			    // delegar al entry point manualmente
			    customAuthenticationEntryPoint.commence(request, response, ex);
			    return;
			}
    }
}