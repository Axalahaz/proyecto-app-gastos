package com.finanzas.app.identity.application.exception;

import com.finanzas.app.shared.exception.extend.ConflictException;

public class EmailYaRegistradoException extends ConflictException{

	public EmailYaRegistradoException() {
		super("El email ya está registrado");
	}

}
