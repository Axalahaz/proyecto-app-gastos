package com.finanzas.app.gastos.categorias.infrastructure.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.finanzas.app.gastos.categorias.domain.CategoriaGasto;
import com.finanzas.app.gastos.categorias.domain.CategoriaGastoRepository;
import com.finanzas.app.gastos.categorias.infrastructure.CategoriaGastoEntityJPA;
import com.finanzas.app.gastos.categorias.infrastructure.CategoriaGastoMapper;
import com.finanzas.app.shared.domain.enums.TipoObjeto;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CategoriaGastoRepositoryImpl implements CategoriaGastoRepository {

	private final CategoriaGastoRepositoryJPA jpaRepository;
	private final CategoriaGastoMapper categoriaGastoMapper;
	
	@Override
	public CategoriaGasto guardar(CategoriaGasto categoria) {
		CategoriaGastoEntityJPA entity = categoriaGastoMapper.mapToEntity(categoria);
		CategoriaGastoEntityJPA guardado = jpaRepository.save(entity);
		return categoriaGastoMapper.mapToDomain(guardado);
	}
	
	@Override
	public Optional<CategoriaGasto> buscarPorId(Long categoriaId, Long usuarioId) {
		return jpaRepository.buscarPorId(categoriaId, usuarioId, TipoObjeto.SISTEMA)
				.map(categoriaGastoMapper::mapToDomain);
	}

	@Override
	public List<CategoriaGasto> listarTodos(Long usuarioId) {
		return jpaRepository.listarTodos(usuarioId, TipoObjeto.SISTEMA)
				.stream()
				.map(categoriaGastoMapper::mapToDomain)
				.toList();
	}


	@Override
	public int eliminar(Long categoriaId, Long usuarioId) {
		return jpaRepository.eliminar(categoriaId, usuarioId, TipoObjeto.USUARIO);		
		
	}

	@Override
	public boolean existePorNombre(String nombre, Long usuarioId) {
		return jpaRepository.existePorNombre(nombre, usuarioId, TipoObjeto.SISTEMA);
	}
	
	@Override
	public boolean existePorId(Long categoriaId, Long usuarioId) {
		return jpaRepository.existePorId(categoriaId, usuarioId, TipoObjeto.SISTEMA);
	}
}
