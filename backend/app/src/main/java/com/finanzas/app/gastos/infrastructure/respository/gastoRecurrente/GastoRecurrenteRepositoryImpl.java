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
	public Optional<GastoRecurrente> buscar(Long gastoRecurrenteId, Long usuarioId) {
		return jpaRepository.findByIdAndUsuarioId(gastoRecurrenteId, usuarioId)
				.map(gastoRecurrenteMapper::mapToDomain);
	}

	@Override
	public List<GastoRecurrente> listarPorEstado(Long usuarioId) {
		return jpaRepository.findByUsuarioIdOrUsuarioIdNull(usuarioId)
				.stream()
				.map(gastoRecurrenteMapper::mapToDomain)
				.toList();
	}
	
	@Override
	public boolean existePorDescripcionYCategoriaGastoId(String descripcion, Long categoriaId) {
		return jpaRepository.existsByDescripcionAndCategoriaGastoId(descripcion, categoriaId);
	}
	
	@Override
	public void eliminar(Long gastoRecurrenteId) {
		jpaRepository.deleteById(gastoRecurrenteId);		
	}

	@Override
	public void eliminarTodos(Long usuarioId) {
		jpaRepository.deleteAllByUsuarioId(usuarioId);			
	}

}
