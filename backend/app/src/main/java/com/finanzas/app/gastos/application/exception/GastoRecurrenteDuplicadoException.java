package com.finanzas.app.gastos.application.exception;

import com.finanzas.app.shared.exception.extend.ConflictException;

public class GastoRecurrenteDuplicadoException extends ConflictException{

	public GastoRecurrenteDuplicadoException() {
		super("El Gasto Recurrente ya está registrado");
	}

}
