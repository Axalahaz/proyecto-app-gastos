package com.finanzas.app.gastos.recurrentes.domain;

import java.util.List;
import java.util.Optional;

public interface ConfGastoRecurrenteRepository {
	
	ConfGastoRecurrente guardar(ConfGastoRecurrente gastoRecurrente);

	// -------------------------------------------------------------
	// Consideran el usuario al que pertenece la categoría.
	// -------------------------------------------------------------
    
	Optional<ConfGastoRecurrente> buscarPorId(Long confGastoRecurrenteId, Long usuarioId);
    
    List<ConfGastoRecurrente> listarTodos(Long usuarioId);

    boolean existePorDescripcion(String descripcion, Long usuarioId);
    
    boolean existePorId(Long confGastoRecurrenteId, Long usuarioId);

    int eliminar(Long confGastoRecurrenteId, Long usuarioId);
    
    int eliminarDatosDeUsuario(Long usuarioId);
}
