package com.finanzas.app.gastos.recurrentes.application.contract;

import com.finanzas.app.shared.domain.enums.Frecuencia;

/**
 * Define el contrato público de comunicación del componente
 * recurrentes con otros componentes de la aplicación.
 */
public interface ConfGastoRecurrenteApplication {
	
	// Comunicacion para comprobar que no haya duplicados
	boolean existePorDescripcion(
		String descripcion,
		Long usuarioId
	);

	// Comunicacion para obtener datos necesarios para registro
	ConfGastoRecurrenteData obtenerDatos(
	    Long configuracionId,
	    Long usuarioId
	);

	// Comunicacion para crear una configuracion nueva
    Long crearDesdeGasto(
        Long usuarioId,
        String descripcion,
        Long categoriaGastoId,
        Frecuencia frecuencia,
        Integer diaVencimiento,
        Integer mesVencimiento
    );
    
    // Comunicacion al eliminar datos asociados al usuario
    void eliminarDatosDeUsuario(Long usuarioId);
}