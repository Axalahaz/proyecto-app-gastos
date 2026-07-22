package com.finanzas.app.identity.application.exception;

import com.finanzas.app.shared.exception.extend.ForbiddenException;

public class ResetPasswordInactivaException extends ForbiddenException{

	public ResetPasswordInactivaException() {
		super("Codigo de reseteo de contraseña usado.");
	}

}
