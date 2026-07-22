package com.finanzas.app.gastos.domain.repository.gasto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.finanzas.app.gastos.domain.entity.Gasto;

// WRITE MODEL

public interface GastoRepository {
	
	Optional<Gasto> buscarPorIdYUsuarioId(Long gastoId, Long usuarioId);
	
    Gasto guardar(Gasto gasto);
    
    void eliminar(Long gastoId);

    void eliminarTodos(Long usuarioId);

    boolean existePagoActivoEnPeriodo(
    		Long gastoRecurrenteId,
			LocalDateTime fechaInicio,
			LocalDateTime fechaFin);

    List<Gasto> listarPorCategoriaYUsuario(Long categoriaGastoId, Long usuarioId);

}
