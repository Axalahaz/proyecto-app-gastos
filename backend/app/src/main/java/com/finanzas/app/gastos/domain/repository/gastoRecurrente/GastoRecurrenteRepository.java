package com.finanzas.app.gastos.domain.repository.gastoRecurrente;

import java.util.List;
import java.util.Optional;

import com.finanzas.app.gastos.domain.entity.GastoRecurrente;

// WRITE MODEL

public interface GastoRecurrenteRepository {
	
	Optional<GastoRecurrente> buscar(Long gastoRecurrenteId);
	
	GastoRecurrente guardar(GastoRecurrente gastoRecurrente);
    
    void eliminar(Long gastoRecurrenteId);
    
    List<GastoRecurrente> listarAll();

    List<GastoRecurrente> listarPorEstado(boolean estado);

    boolean existePorDescripcion(String descripcion);

}
