package com.finanzas.app.gastos.domain.repository.plantillaGastoRecurrente;

import java.util.List;
import java.util.Optional;

import com.finanzas.app.gastos.domain.entity.PlantillaGastoRecurrente;

// WRITE MODEL

public interface PlantillaGastoRecurrenteRepository {
	
	Optional<PlantillaGastoRecurrente> buscarPorId(Long plantillaId);
	
	PlantillaGastoRecurrente guardar(PlantillaGastoRecurrente plantillaGastoRecurrente);
	
	boolean existePorDescripcion(String descripcion);

	boolean existePorId(Long plantillaId);
    
    List<PlantillaGastoRecurrente> listar(); 

    void eliminar(Long plantillaId); 

}
