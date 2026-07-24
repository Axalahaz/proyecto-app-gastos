package com.finanzas.app.gastos.application.exception;

import com.finanzas.app.shared.exception.extend.ConflictException;

public class CategoriaGastoDuplicadaException extends ConflictException{

	public CategoriaGastoDuplicadaException() {
		super("La Categoria de Gasto ya está registrada");
	}

}
