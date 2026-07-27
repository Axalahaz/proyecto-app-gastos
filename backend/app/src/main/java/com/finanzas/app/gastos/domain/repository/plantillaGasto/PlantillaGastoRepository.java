package com.finanzas.app.gastos.domain.repository.plantillaGasto;

import java.util.List;
import java.util.Optional;

import com.finanzas.app.gastos.domain.entity.PlantillaGasto;
import com.finanzas.app.shared.domain.model.TipoObjeto;


// WRITE MODEL

public interface PlantillaGastoRepository {
	
	PlantillaGasto guardar(PlantillaGasto plantillaGasto);
	
	boolean existePorDescripcionYCategoriaGastoId(String descripcion, Long categoriaGastoId);

	boolean existePorCategoriaGastoId(Long categoriaGastoId);

	boolean existePorId(Long plantillaId);
    
	void eliminar(Long plantillaId); 

	Optional<PlantillaGasto> buscarPorId(Long plantillaId);
    
	List<PlantillaGasto> listarAll(); 
	
	List<PlantillaGasto> listarPorTipo(TipoObjeto tipo);
}
