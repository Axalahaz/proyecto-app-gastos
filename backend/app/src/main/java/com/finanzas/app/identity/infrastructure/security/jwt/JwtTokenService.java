package com.finanzas.app.identity.infrastructure.security.jwt;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.finanzas.app.identity.application.port.out.GeneradorTokenAcceso;
import com.finanzas.app.identity.domain.Usuario;
import com.finanzas.app.identity.infrastructure.security.exception.TokenInvalidoException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

/**
 * Servicio de infraestructura encargado de gestionar la generación
 * y validación de tokens JWT.
 *
 * Implementa el contrato {@link GeneradorTokenAcceso}, proporcionando
 * la generación de tokens de acceso mediante una clave criptográfica.
 *
 * También gestiona la generación de tokens de recuperación de contraseña
 * y la extracción de claims, verificando que cada token corresponda
 * al propósito para el cual fue generado.
 *
 * Utiliza el algoritmo HMAC para firmar los tokens y una clave secreta
 * configurada externamente mediante las propiedades de la aplicación.
 *
 * @see GeneradorTokenAcceso
 * @see Usuario
 */
@Service
public class JwtTokenService implements GeneradorTokenAcceso {
	
	// Tiempo de expiración de los tokens: 15 minutos.
		private static final long EXPIRACION_TOKEN_MS =
		        1000L * 60 * 15;
		
	/**
     * Clave criptográfica utilizada para firmar y verificar los tokens JWT.
     *
     * Se obtiene a partir de la clave secreta configurada en la aplicación
     * y se transforma al tipo {@link SecretKey} para utilizarla
     * en las operaciones criptográficas.
     */
	private final SecretKey key;
	
	/**
     * Inicializa el servicio a partir de la clave secreta configurada
     * en la aplicación.
     *
     * Spring inyecta el valor de la propiedad {@code jwt.secret},
     * que se convierte en una clave criptográfica compatible con HMAC.
     *
     * La clave debe cumplir con la longitud mínima requerida
     * por el algoritmo de firma utilizado.
     *
     * @param secret clave secreta utilizada para firmar y verificar
     *               los tokens JWT.
     */
	public JwtTokenService(
			@Value("${jwt.secret}") String secret
		) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }
	
	// -----------------------------------------------------
	
	/**
     * Genera un token JWT de acceso asociado a un usuario.
     *
     * El token incluye el identificador del usuario, su propósito
     * de acceso y las fechas de emisión y expiración.
     *
     * Su validez está limitada a 15 minutos.
     *
     * @param usuario usuario al que se asociará el token.
     * @return token JWT de acceso firmado.
     */
	@Override
    public String generarToken(Usuario usuario) {
    	
        return Jwts.builder()
                .setSubject(String.valueOf(usuario.getId()))	// identificador 
                .claim("purpose", "access")	// flag para saber que es token de acceso
                .setIssuedAt(new Date())	// fecha de emisión
                .setExpiration(
                		new Date(System.currentTimeMillis() + EXPIRACION_TOKEN_MS) // expiración
                ) 
                .signWith(key, SignatureAlgorithm.HS256)	// firma con clave secreta
                .compact();
    }
    
	
	/**
     * Genera un token JWT destinado a la recuperación de contraseña.
     *
     * Incluye el identificador del usuario, la versión de recuperación
     * de contraseña y el propósito específico del token.
     *
     * La versión permite identificar e invalidar tokens de recuperación
	 * anteriores cuando cambia la versión almacenada en el usuario.
	 *
	 * Su validación debe realizarse al procesar la solicitud de recuperación.
     *
     * Su validez está limitada a 15 minutos.
     *
     * @param usuario usuario al que se asociará el token.
     * @return token JWT de recuperación de contraseña firmado.
     */
    public String generarTokenRecuperacion(Usuario usuario) {

        return Jwts.builder()
                .setSubject(String.valueOf(usuario.getId()))
                .claim("resetVersion", usuario.getResetPasswordVersion())	// sirve para invalidar el token de reset
                .claim("purpose", "reset") // flag para saber que es token de recuperación
                .setIssuedAt(new Date())
                .setExpiration(
                		new Date(System.currentTimeMillis() + EXPIRACION_TOKEN_MS)
                )
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
    
    // -----------------------------------------------------
    
    /**
     * Extrae los claims de un token JWT después de verificar su firma
     * y su período de validez.
     *
     * La biblioteca JWT realiza automáticamente la comprobación
     * de expiración durante el análisis del token.
     *
     * @param token token JWT del que se desean obtener los claims.
     * @return información contenida en el cuerpo del token.
     * @throws io.jsonwebtoken.JwtException si el token es inválido,
     *                                      está vencido o no supera
     *                                      las validaciones de JWT.
     */
    public Claims extraerClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    
    // -----------------------------------------------------
    
    /**
     * Obtiene los claims de un token JWT destinado al acceso.
     *
     * Además de las validaciones criptográficas y temporales,
     * verifica que el propósito declarado en el token sea {@code access}.
     *
     * @param token token JWT de acceso.
     * @return claims del token validado.
     * @throws TokenInvalidoException si el propósito del token
     *                                     no corresponde al acceso.
     */
    public Claims obtenerClaimsTokenSeguro(String token) {
        Claims claims = extraerClaims(token);

        if (!"access".equals(claims.get("purpose", String.class))) {
            throw new TokenInvalidoException("Tipo de token inválido");
        }

        return claims;
    }
    
    /**
     * Obtiene los claims de un token JWT destinado a la recuperación
     * de contraseña.
     *
     * Además de las validaciones criptográficas y temporales,
     * verifica que el propósito declarado en el token sea {@code reset}.
     *
     * @param token token JWT de recuperación de contraseña.
     * @return claims del token validado.
     * @throws TokenInvalidoException si el propósito del token
     *                                     no corresponde a la recuperación.
     */
    public Claims obtenerClaimsTokenRecuperacionSeguro(String token) {
        Claims claims = extraerClaims(token);

        if (!"reset".equals(claims.get("purpose", String.class))) {
            throw new TokenInvalidoException("Tipo de token inválido");
        }

        // Expiración se valida automáticamente al extraer claims si usás parserBuilder()
        return claims;
    }
}
