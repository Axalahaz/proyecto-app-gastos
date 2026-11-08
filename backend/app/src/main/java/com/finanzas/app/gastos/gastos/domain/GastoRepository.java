package com.finanzas.app.gastos.gastos.domain;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

// WRITE MODEL

public interface GastoRepository {
	
	Gasto guardar(Gasto gasto);
	
	// - Consideran el usuario al que pertenece la categoría.
	
	Optional<Gasto> buscarPorId(Long gastoId, Long usuarioId);
    
    int eliminar(Long gastoId, Long usuarioId);

    boolean existePorConfGastoRecurrenteId(Long confGastoRecurrenteId, Long usuarioId);
    		
    boolean existePagoActivoEnPeriodo(
    		Long confGastoRecurrenteId,
			LocalDateTime fechaInicio,
			LocalDateTime fechaFin,
			Long usuarioId);

    List<Gasto> listarTodos(Long usuarioId);

}
