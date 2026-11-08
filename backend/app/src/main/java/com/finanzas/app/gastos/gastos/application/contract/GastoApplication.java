package com.finanzas.app.gastos.gastos.application.contract;

/**
 * Define el contrato público de comunicación del componente
 * recurrentes con otros componentes de la aplicación.
 */
public interface GastoApplication {
	
	// Comunicacion para indicar si se creo a partir de una configuracion recurrente
	boolean existePorConfGastoRecurrenteId(
		Long confGastoRecurrenteId,
		Long usuarioId
	);

	// Comunicacion para obtener datos necesarios para registro
	GastoData obtenerDatos(
	    Long gastoId,
	    Long usuarioId
	);

}