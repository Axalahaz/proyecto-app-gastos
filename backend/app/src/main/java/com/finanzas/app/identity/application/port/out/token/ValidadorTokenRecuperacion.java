package com.finanzas.app.identity.application.port.out.token;

public interface ValidadorTokenRecuperacion {

    TokenRecuperacionData validarTokenRecuperacionSeguro(String token);
}