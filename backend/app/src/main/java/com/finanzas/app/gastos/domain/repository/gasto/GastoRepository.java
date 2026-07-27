package com.finanzas.app.gastos.domain.repository.gasto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.finanzas.app.gastos.domain.entity.Gasto;

// WRITE MODEL

public interface GastoRepository {
	
	Gasto guardar(Gasto gasto);
	
	Optional<Gasto> buscarPorId(Long gastoId);
    
    void eliminar(Long gastoId);

    boolean existePorGastoRecurrenteId(Long gastoRecurrenteId);
    		
    boolean existePagoActivoEnPeriodo(
    		Long gastoRecurrenteId,
			LocalDateTime fechaInicio,
			LocalDateTime fechaFin);

    List<Gasto> listarPorCategoria(Long categoriaGastoId);

    List<Gasto> listarTodos();

}
