package com.finanzas.app.gastos.application.exception;

import com.finanzas.app.shared.exception.extend.ConflictException;

public class PlantillaGastoRecurrenteDuplicadaException extends ConflictException{

	public PlantillaGastoRecurrenteDuplicadaException() {
		super("La Plantilla de Gasto Recurrente ya está registrada");
	}

}
