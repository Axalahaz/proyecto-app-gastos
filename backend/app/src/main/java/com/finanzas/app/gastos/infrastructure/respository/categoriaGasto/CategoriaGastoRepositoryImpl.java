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
	public Optional<CategoriaGasto> buscarPorId(Long categoriaId) {
		return jpaRepository.findById(categoriaId)
				.map(categoriaGastoMapper::mapToDomain);
	}


	@Override
	public List<CategoriaGasto> listarAll() {
		return jpaRepository.findAllByOrderByNombreAsc()
				.stream()
				.map(categoriaGastoMapper::mapToDomain)
				.toList();
	}

	@Override
	public List<CategoriaGasto> listarPorTipo(TipoObjeto tipo) {
		return jpaRepository.findByTipoOrderByNombreAsc(tipo)
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
	public boolean existePorNombre(String nombre) {
		return jpaRepository.existsByNombre(nombre);
	}
	
	@Override
	public boolean existePorId(Long categoriaId) {
		return jpaRepository.existsById(categoriaId);
	}

}
