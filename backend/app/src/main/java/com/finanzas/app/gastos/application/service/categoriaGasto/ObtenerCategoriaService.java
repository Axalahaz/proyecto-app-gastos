package com.finanzas.app.gastos.application.service.categoriaGasto;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.gastos.application.mapper.CategoriaGastoApplicationMapper;
import com.finanzas.app.gastos.domain.entity.CategoriaGasto;
import com.finanzas.app.gastos.domain.repository.categoriaGasto.CategoriaGastoRepository;
import com.finanzas.app.shared.domain.UsuarioAutenticado;
import com.finanzas.app.shared.domain.model.TipoObjeto;
import com.finanzas.app.shared.dto.context.CategoriaResponse;
import com.finanzas.app.shared.exception.extend.NotFoundException;

import lombok.RequiredArgsConstructor;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class ObtenerCategoriaService {

	private final CategoriaGastoRepository categoriaRepository;
    private final UsuarioAutenticado usuarioAutenticado;
    private final CategoriaGastoApplicationMapper applicationMapper;

    public CategoriaResponse ejecutar(Long categoriaGastoId) {
    	
    	CategoriaGasto categoria;
    	
    	// ADMIN
    	if (usuarioAutenticado.esAdmin()) {
            categoria = categoriaRepository.buscarPorIdYTipo(categoriaGastoId, TipoObjeto.SISTEMA)
                    .orElseThrow(() -> NotFoundException.of("Categoria de Gasto", categoriaGastoId));
        } else {
        	// USER
        	Long userId = usuarioAutenticado.obtenerId();
        	
        	categoria = categoriaRepository.buscarPorIdYUsuario(categoriaGastoId, userId)
        			.or(() -> categoriaRepository.buscarPorIdYTipo(categoriaGastoId, TipoObjeto.SISTEMA))
        			.orElseThrow(() -> NotFoundException.of("Categoria de Gasto", categoriaGastoId));
        }
    	
    	return applicationMapper.mapToResponse(categoria);
    }
}
