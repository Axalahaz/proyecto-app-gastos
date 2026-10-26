package com.finanzas.app.gastos.categorias.domain;

import java.util.List;
import java.util.Optional;

public interface CategoriaGastoRepository {
	
	CategoriaGasto guardar(CategoriaGasto categoria);

	// -------------------------------------------------------------
	/*
	 * Consultas:
	 * - Consideran el usuario al que pertenece la categoría.
	 * - Respetan el tipo de objeto: SISTEMA o USUARIO, según corresponda a la operación.
	 */
	// -------------------------------------------------------------

	// Verifica duplicados
	boolean existePorNombre(String nombre, Long usuarioId);

	boolean existePorId(Long categoriaId, Long usuarioId);

	Optional<CategoriaGasto> buscarPorId(Long categoriaId, Long usuarioId);
	
	List<CategoriaGasto> listarTodos(Long usuarioId);

	int eliminar(Long categoriaId, Long usuarioId);
}
