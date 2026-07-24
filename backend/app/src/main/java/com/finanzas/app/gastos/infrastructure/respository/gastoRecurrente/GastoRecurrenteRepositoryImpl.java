package com.finanzas.app.gastos.infrastructure.respository.gastoRecurrente;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.finanzas.app.gastos.domain.entity.GastoRecurrente;
import com.finanzas.app.gastos.domain.repository.gastoRecurrente.GastoRecurrenteRepository;
import com.finanzas.app.gastos.infrastructure.mapper.GastoRecurrenteMapper;
import com.finanzas.app.gastos.infrastructure.entity.GastoRecurrenteEntityJPA;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class GastoRecurrenteRepositoryImpl implements GastoRecurrenteRepository{
	
	private final GastoRecurrenteRepositoryJPA jpaRepository;
	private final GastoRecurrenteMapper gastoRecurrenteMapper;
	
	@Override
	public GastoRecurrente guardar(GastoRecurrente gastoRecurrente) {
		GastoRecurrenteEntityJPA entity = gastoRecurrenteMapper.mapToEntity(gastoRecurrente);
		GastoRecurrenteEntityJPA guardado = jpaRepository.save(entity);
		return gastoRecurrenteMapper.mapToDomain(guardado);
	}

	@Override
	public Optional<GastoRecurrente> buscar(Long gastoRecurrenteId) {
		return jpaRepository.findById(gastoRecurrenteId)
				.map(gastoRecurrenteMapper::mapToDomain);
	}

	@Override
	public List<GastoRecurrente> listarAll() {
		return jpaRepository.findAll()
				.stream()
				.map(gastoRecurrenteMapper::mapToDomain)
				.toList();
	}
	
	@Override
	public List<GastoRecurrente> listarPorEstado(boolean estado) {
		return jpaRepository.findByActivo(estado)
				.stream()
				.map(gastoRecurrenteMapper::mapToDomain)
				.toList();
	}
	
	@Override
	public boolean existePorDescripcion(String descripcion) {
		return jpaRepository.existsByDescripcion(descripcion);
	}
	
	@Override
	public void eliminar(Long gastoRecurrenteId) {
		jpaRepository.deleteById(gastoRecurrenteId);		
	}
}
