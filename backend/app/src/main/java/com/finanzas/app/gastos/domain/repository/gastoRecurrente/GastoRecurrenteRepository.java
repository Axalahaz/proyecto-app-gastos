package com.finanzas.app.gastos.domain.repository.gastoRecurrente;

import java.util.List;
import java.util.Optional;

import com.finanzas.app.gastos.domain.entity.GastoRecurrente;

// WRITE MODEL

public interface GastoRecurrenteRepository {
	
	Optional<GastoRecurrente> buscar(Long gastoRecurrenteId, Long userId);
	
	GastoRecurrente guardar(GastoRecurrente gastoRecurrente);
    
    void eliminar(Long gastoRecurrenteId);
    
    void eliminarTodos(Long usuarioId);

    List<GastoRecurrente> listarPorEstado(Long userId);

    boolean existePorDescripcionYCategoriaGastoId(String descripcion, Long categoriaId);

}
