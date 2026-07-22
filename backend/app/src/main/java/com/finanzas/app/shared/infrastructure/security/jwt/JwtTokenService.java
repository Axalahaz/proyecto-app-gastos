package com.finanzas.app.shared.infrastructure.security.jwt;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.finanzas.app.identity.application.exception.InvalidCredentialsException;
import com.finanzas.app.identity.domain.entity.Usuario;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtTokenService {
	
	// Clave criptográfica ya transformada a SecretKey (no se usa String directamente)
	private final SecretKey key;
	
    // -----------------------------------------------------
	// CONSTRUCTOR
	
	// Spring inyecta la propiedad jwt.secret desde application.properties ⚠️ MÍNIMO 32 caracteres para HS256
	public JwtTokenService(
			@Value("${jwt.secret}") String secret
			) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }
	
	// -----------------------------------------------------
	// GENERAR TOKEN FIRMADO DE CONEXION
	
    public String generarToken(Usuario usuario) {
    	long expirationMs = 1000 * 60 * 15; // 15 minutos
    	
        return Jwts.builder()
                .setSubject(String.valueOf(usuario.getId()))	// identificador 
                .setIssuedAt(new Date())		// fecha de emisión
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs)) // expiración
                .signWith(key)					// firma con clave secreta
                .compact();
    }
    
	
	// -----------------------------------------------------
	// GENERAR TOKEN DE RECUPERACION DE PASSWORD

    public String generarTokenRecuperacion(Usuario usuario) {
        long expirationMs = 1000 * 60 * 15; // 15 minutos

        return Jwts.builder()
                .setSubject(String.valueOf(usuario.getId()))
                .claim("resetVersion", usuario.getResetPasswordVersion())	// sirve para invalidar el token de reset
                .claim("purpose", "reset") // flag para saber que es token de recuperación
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(key)
                .compact();
    }
    
    // -----------------------------------------------------
    // OBTENER CLAIMS
    
    public Claims extraerClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    
    // -----------------------------------------------------
    // OBTENER TOKENS
    
    public Claims obtenerClaimsTokenSeguro(String token) {
    	return extraerClaims(token);
    }
    
    public Claims obtenerClaimsTokenRecuperacionSeguro(String token) {
        Claims claims = extraerClaims(token);

        // Verifica que sea un token de propósito reset
        if (!"reset".equals(claims.get("purpose", String.class))) {
            throw new InvalidCredentialsException("Credenciales invalidas");
        }

        // Expiración se valida automáticamente al extraer claims si usás parserBuilder()
        return claims;
    }
}
