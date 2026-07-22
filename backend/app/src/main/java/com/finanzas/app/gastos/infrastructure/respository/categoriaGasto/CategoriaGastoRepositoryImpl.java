package com.finanzas.app.gastos.infrastructure.respository.categoriaGasto;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.finanzas.app.gastos.domain.entity.CategoriaGasto;
import com.finanzas.app.gastos.domain.repository.categoriaGasto.CategoriaGastoRepository;
import com.finanzas.app.gastos.infrastructure.entity.CategoriaGastoEntityJPA;
import com.finanzas.app.gastos.infrastructure.mapper.CategoriaGastoMapper;
import com.finanzas.app.shared.domain.model.TipoObjeto;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CategoriaGastoRepositoryImpl implements CategoriaGastoRepository {

	private final CategoriaGastoRepositoryJPA jpaRepository;
	private final CategoriaGastoMapper categoriaGastoMapper;
	
	@Override
	public Optional<CategoriaGasto> buscarPorIdYUsuario(Long categoriaId, Long usuarioId) {
		return jpaRepository.findByIdAndUsuarioId(categoriaId, usuarioId)
				.map(categoriaGastoMapper::mapToDomain);
	}

	@Override
	public Optional<CategoriaGasto> buscarDisponible(Long categoriaId, Long usuarioId) {
		return jpaRepository.buscarDisponible(categoriaId, usuarioId)
				.map(categoriaGastoMapper::mapToDomain);
	}
	
	@Override
	public Optional<CategoriaGasto> buscarPorIdYTipo(Long categoriaId, TipoObjeto tipo) {
		return jpaRepository.findByIdAndTipo(categoriaId, tipo)
				.map(categoriaGastoMapper::mapToDomain);
	}

	@Override
	public boolean existePorIdYUsuarioId(Long categoriaId, Long usuarioId) {
		return jpaRepository.existsByIdAndUsuarioId(categoriaId, usuarioId);
	}

	@Override
	public List<CategoriaGasto> listarPorUsuario(Long usuarioId) {
		return jpaRepository.findByUsuarioId(usuarioId)
				.stream()
				.map(categoriaGastoMapper::mapToDomain)
				.toList();
	}

	@Override
	public List<CategoriaGasto> listarPorTipo(TipoObjeto tipo) {
		return jpaRepository.findByTipo(tipo)
				.stream()
				.map(categoriaGastoMapper::mapToDomain)
				.toList();
	}

	@Override
	public CategoriaGasto guardar(CategoriaGasto categoria) {
		CategoriaGastoEntityJPA entity = categoriaGastoMapper.mapToEntity(categoria);
		CategoriaGastoEntityJPA guardado = jpaRepository.save(entity);
		return categoriaGastoMapper.mapToDomain(guardado);
	}

	@Override
	public void eliminar(Long categoriaId) {
		jpaRepository.deleteById(categoriaId);		
		
	}

	@Override
	public void eliminarTodos(Long usuarioId) {
		jpaRepository.deleteAllByUsuarioId(usuarioId);		
	}

}
