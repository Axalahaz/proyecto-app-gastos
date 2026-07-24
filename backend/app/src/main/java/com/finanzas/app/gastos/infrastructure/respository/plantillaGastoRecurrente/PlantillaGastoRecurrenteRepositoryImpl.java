package com.finanzas.app.gastos.infrastructure.respository.plantillaGastoRecurrente;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.finanzas.app.gastos.domain.entity.PlantillaGastoRecurrente;
import com.finanzas.app.gastos.domain.repository.plantillaGastoRecurrente.PlantillaGastoRecurrenteRepository;
import com.finanzas.app.gastos.infrastructure.mapper.PlantillaGastoRecurrenteMapper;
import com.finanzas.app.gastos.infrastructure.entity.PlantillaGastoRecurrenteEntityJPA;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class PlantillaGastoRecurrenteRepositoryImpl implements PlantillaGastoRecurrenteRepository{
	
	private final PlantillaGastoRecurrenteRepositoryJPA jpaRepository;
	private final PlantillaGastoRecurrenteMapper mapper;
	
	@Override
	public PlantillaGastoRecurrente guardar(PlantillaGastoRecurrente plantilla) {
		PlantillaGastoRecurrenteEntityJPA entity = mapper.mapToEntity(plantilla);
		PlantillaGastoRecurrenteEntityJPA guardado = jpaRepository.save(entity);
		return mapper.mapToDomain(guardado);
	}

	@Override
	public List<PlantillaGastoRecurrente> listar() {
		return jpaRepository.findAll()
				.stream()
				.map(mapper::mapToDomain)
				.toList();
	}

	@Override
	public Optional<PlantillaGastoRecurrente> buscarPorId(Long plantillaId) {
		return jpaRepository.findById(plantillaId)
				.map(mapper::mapToDomain);
	}
	
	@Override
	public boolean existePorDescripcion(String descripcion) {
		return jpaRepository.existsByDescripcion(descripcion);
	}

	@Override
	public boolean existePorId(Long plantillaId) {
		return jpaRepository.existsById(plantillaId);
	}

	@Override
	public void eliminar(Long plantillaId) {
		jpaRepository.deleteById(plantillaId);
	}
	

}