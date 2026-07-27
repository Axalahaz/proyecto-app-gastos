package com.finanzas.app.gastos.infrastructure.respository.plantillaGasto;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.finanzas.app.gastos.domain.entity.PlantillaGasto;
import com.finanzas.app.gastos.domain.repository.plantillaGasto.PlantillaGastoRepository;
import com.finanzas.app.gastos.infrastructure.entity.PlantillaGastoEntityJPA;
import com.finanzas.app.gastos.infrastructure.mapper.PlantillaGastoMapper;
import com.finanzas.app.shared.domain.model.TipoObjeto;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class PlantillaGastoRepositoryImpl implements PlantillaGastoRepository{
	
	private final PlantillaGastoRepositoryJPA jpaRepository;
	private final PlantillaGastoMapper mapper;
	
	@Override
	public PlantillaGasto guardar(PlantillaGasto plantilla) {
		PlantillaGastoEntityJPA entity = mapper.mapToEntity(plantilla);
		PlantillaGastoEntityJPA guardado = jpaRepository.save(entity);
		return mapper.mapToDomain(guardado);
	}

	@Override
	public List<PlantillaGasto> listarAll() {
		return jpaRepository.findAllByOrderByNombreAsc()
				.stream()
				.map(mapper::mapToDomain)
				.toList();
	}
	
	@Override
	public List<PlantillaGasto> listarPorTipo(TipoObjeto tipo) {
		return jpaRepository.findByTipoOrderByNombreAsc(tipo)
				.stream()
				.map(mapper::mapToDomain)
				.toList();
	}

	@Override
	public Optional<PlantillaGasto> buscarPorId(Long plantillaId) {
		return jpaRepository.findById(plantillaId)
				.map(mapper::mapToDomain);
	}
	
	@Override
	public boolean existePorDescripcionYCategoriaGastoId(String descripcion, Long categoriaGastoId) {
		return jpaRepository.existsByDescripcionAndCategoriaGastoId(descripcion, categoriaGastoId);
	}

	@Override
	public boolean existePorId(Long plantillaId) {
		return jpaRepository.existsById(plantillaId);
	}

	@Override
	public boolean existePorCategoriaGastoId(Long categoriaGastoId) {
		return jpaRepository.existsByCategoriaGastoId(categoriaGastoId);
	}

	@Override
	public void eliminar(Long plantillaId) {
		jpaRepository.deleteById(plantillaId);
	}
	

}