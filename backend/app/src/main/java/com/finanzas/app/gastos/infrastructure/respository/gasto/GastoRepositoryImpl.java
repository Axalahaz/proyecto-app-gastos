package com.finanzas.app.gastos.infrastructure.respository.gasto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.finanzas.app.gastos.domain.entity.Gasto;
import com.finanzas.app.gastos.domain.repository.gasto.GastoRepository;
import com.finanzas.app.gastos.infrastructure.mapper.GastoMapper;
import com.finanzas.app.gastos.infrastructure.entity.GastoEntityJPA;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class GastoRepositoryImpl implements GastoRepository{
	
	private final GastoRepositoryJPA jpaRepository;
	private final GastoMapper gastoMapper;
	
	@Override
	public Gasto guardar(Gasto gasto) {
		GastoEntityJPA gastoEntity = gastoMapper.mapToEntity(gasto);
		GastoEntityJPA guardado = jpaRepository.save(gastoEntity);
		return gastoMapper.mapToDomain(guardado);
	}

	@Override
	public Optional<Gasto> buscarPorId(Long gastoId) {
		return jpaRepository.findById(gastoId)
				.map(gastoMapper::mapToDomain);
	}



	@Override
	public List<Gasto> listarPorCategoria(Long categoriaGastoId) {
		return jpaRepository.findByCategoriaGastoId(categoriaGastoId)
				.stream()
				.map(gastoMapper::mapToDomain)
				.toList();
	}

	@Override
	public boolean existePagoActivoEnPeriodo(
			Long gastoRecurrenteId,
			LocalDateTime fechaInicio,
			LocalDateTime fechaFin
			) {
		return jpaRepository.existePagoActivoEnPeriodo(
				gastoRecurrenteId, fechaInicio, fechaFin);
	}

	@Override
	public void eliminar(Long gastoId) {
		jpaRepository.deleteById(gastoId);		
	}
}
