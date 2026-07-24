package com.finanzas.app.shared.exception.global;

import com.finanzas.app.shared.exception.extend.ConflictException;

public class RecursoDuplicadoException extends ConflictException{

	public RecursoDuplicadoException(String detalle) {
		super("Recurso duplicado: " + detalle);
	}

}
