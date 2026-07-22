package com.finanzas.app.identity.application.exception;

import com.finanzas.app.shared.exception.extend.ForbiddenException;

public class UsuarioInactivoException extends ForbiddenException {

    public UsuarioInactivoException() {
        super("El usuario esta inactivo");
    }
}
