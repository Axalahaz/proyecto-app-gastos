package com.finanzas.app.gastos.recurrentes.domain;

import java.util.List;
import java.util.Optional;

// WRITE MODEL

public interface GastoRecurrenteRepository {
	
	Optional<GastoRecurrente> buscar(Long gastoRecurrenteId);
	
	GastoRecurrente guardar(GastoRecurrente gastoRecurrente);
    
    void eliminar(Long gastoRecurrenteId);
    
    List<GastoRecurrente> listarAll();

    List<GastoRecurrente> listarPorEstado(boolean estado);

    boolean existePorDescripcion(String descripcion);

}
