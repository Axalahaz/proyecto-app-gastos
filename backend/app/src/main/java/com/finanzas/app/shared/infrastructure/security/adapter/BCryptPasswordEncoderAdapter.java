package com.finanzas.app.shared.infrastructure.security.adapter;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.finanzas.app.identity.domain.PasswordEncoder;

/*
 * Implementa tu interfaz del dominio
 * Usa spring security {BCryptPasswordEncoder}
 * 
 * */

@Component
public class BCryptPasswordEncoderAdapter implements PasswordEncoder {

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    // Dominio pide encriptar → infraestructura usa BCrypt
    @Override
    public String encode(String rawPassword) {
        return encoder.encode(rawPassword);
    }

    // Dominio pregunta si coinciden → infraestructura verifica con BCrypt
    @Override
    public boolean matches(String rawPassword, String encodedPassword) {
        return encoder.matches(rawPassword, encodedPassword);
    }
}