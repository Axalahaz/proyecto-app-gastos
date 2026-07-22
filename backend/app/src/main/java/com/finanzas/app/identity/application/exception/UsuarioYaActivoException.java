package com.finanzas.app.identity.application.exception;

import com.finanzas.app.shared.exception.extend.ForbiddenException;

public class UsuarioYaActivoException extends ForbiddenException {

    public UsuarioYaActivoException() {
        super("El usuario esta activo");
    }
}
