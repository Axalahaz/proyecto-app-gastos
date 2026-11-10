package com.finanzas.app.gastos.gastos.infrastructure.respository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.finanzas.app.gastos.gastos.domain.Gasto;
import com.finanzas.app.gastos.gastos.domain.GastoRepository;
import com.finanzas.app.gastos.gastos.infrastructure.GastoEntityJPA;
import com.finanzas.app.gastos.gastos.infrastructure.GastoMapper;
import com.finanzas.app.gastos.recurrentes.domain.EstadoConf;

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
	public Optional<Gasto> buscarPorId(Long gastoId, Long usuarioId) {
		return jpaRepository.findByIdAndUsuarioId(gastoId, usuarioId)
				.map(gastoMapper::mapToDomain);
	}

	@Override
	public int eliminar(Long gastoId, Long usuarioId) {
		return jpaRepository.eliminar(gastoId, usuarioId, EstadoConf.ACTIVA);		
	}
	
	@Override
	public List<Gasto> listarTodos(Long usuarioId) {
		return jpaRepository.findByUsuarioId(usuarioId)
				.stream()
				.map(gastoMapper::mapToDomain)
				.toList();
	}

	@Override
	public boolean existePagoActivoEnPeriodo(
			Long confGastoRecurrenteId,
			LocalDateTime fechaInicio,
			LocalDateTime fechaFin,
			Long usuarioId
			) {
		return jpaRepository.existePagoActivoEnPeriodo(
				confGastoRecurrenteId, fechaInicio, fechaFin, usuarioId);
	}

	@Override
	public boolean existePorConfGastoRecurrenteId(Long confGastoRecurrenteId, Long usuarioId) {
		return jpaRepository.existsByConfGastoRecurrenteIdAndUsuarioId(confGastoRecurrenteId, usuarioId);
	}

	@Override
	public int contadorTotalGastos(Long usuarioId) {
		return jpaRepository.contadorTotalGastos(usuarioId);
	}

	@Override
	public int eliminarDatosDeUsuario(Long usuarioId) {
		return jpaRepository.eliminarDatosDeUsuario(usuarioId);
	}

	
}
