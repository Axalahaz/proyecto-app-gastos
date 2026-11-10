package com.finanzas.app.gastos.recurrentes.infrastructure.respository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.finanzas.app.gastos.recurrentes.domain.ConfGastoRecurrente;
import com.finanzas.app.gastos.recurrentes.domain.ConfGastoRecurrenteRepository;
import com.finanzas.app.gastos.recurrentes.infrastructure.ConfGastoRecurrenteEntityJPA;
import com.finanzas.app.gastos.recurrentes.infrastructure.ConfGastoRecurrenteMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ConfGastoRecurrenteRepositoryImpl implements ConfGastoRecurrenteRepository{
	
	private final ConfGastoRecurrenteRepositoryJPA jpaRepository;
	private final ConfGastoRecurrenteMapper confGastoRecurrenteMapper;
	
	@Override
	public ConfGastoRecurrente guardar(ConfGastoRecurrente confGastoRecurrente) {
		ConfGastoRecurrenteEntityJPA entity = confGastoRecurrenteMapper.mapToEntity(confGastoRecurrente);
		ConfGastoRecurrenteEntityJPA guardado = jpaRepository.save(entity);
		return confGastoRecurrenteMapper.mapToDomain(guardado);
	}

	@Override
	public Optional<ConfGastoRecurrente> buscarPorId(Long confGastoRecurrenteId, Long usuarioId) {
		return jpaRepository.buscarPorId(confGastoRecurrenteId, usuarioId)
				.map(confGastoRecurrenteMapper::mapToDomain);
	}

	@Override
	public List<ConfGastoRecurrente> listarTodos(Long usuarioId) {
		return jpaRepository.findAllByUsuarioIdOrderByNombreAsc(usuarioId)
				.stream()
				.map(confGastoRecurrenteMapper::mapToDomain)
				.toList();
	}

	@Override
	public boolean existePorDescripcion(String descripcion, Long usuarioId) {
		return jpaRepository.existePorDescripcion(descripcion, usuarioId);
	}
	
	@Override
	public int eliminar(Long confGastoRecurrente, Long usuarioId) {
		return jpaRepository.eliminar(confGastoRecurrente, usuarioId);		
	}

	@Override
	public boolean existePorId(Long confGastoRecurrenteId, Long usuarioId) {
		return jpaRepository.existePorId(confGastoRecurrenteId, usuarioId);
	}
	
	@Override
	public int contadorTotalConfGastoRecurrente(Long usuarioId) {
		return jpaRepository.contadorTotalConfGastoRecurrente(usuarioId);
	}

	@Override
	public int eliminarDatosDeUsuario(Long usuarioId) {
		return jpaRepository.eliminarDatosDeUsuario(usuarioId);
	}
}
