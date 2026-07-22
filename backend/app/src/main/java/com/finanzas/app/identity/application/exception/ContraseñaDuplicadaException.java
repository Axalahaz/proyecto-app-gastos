package com.finanzas.app.identity.application.exception;

import com.finanzas.app.shared.exception.extend.ConflictException;

public class ContraseñaDuplicadaException extends ConflictException{

	public ContraseñaDuplicadaException() {
		super("La nueva contraseña debe ser diferente a la actual");
	}

}
