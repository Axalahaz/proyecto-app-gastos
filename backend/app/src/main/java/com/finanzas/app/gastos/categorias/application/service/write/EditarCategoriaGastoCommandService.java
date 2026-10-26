package com.finanzas.app.gastos.categorias.application.service.write;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.gastos.categorias.application.CategoriaGastoApplicationMapper;
import com.finanzas.app.gastos.categorias.application.CategoriaGastoApplicationQuery;
import com.finanzas.app.gastos.categorias.domain.CategoriaGasto;
import com.finanzas.app.gastos.categorias.domain.CategoriaGastoRepository;
import com.finanzas.app.gastos.categorias.presentation.dto.CategoriaGastoResponse;
import com.finanzas.app.shared.application.security.UsuarioAutenticado;
import com.finanzas.app.shared.exception.exceptionImpl.ConflictException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class EditarCategoriaGastoCommandService {

	private final CategoriaGastoApplicationQuery categoriaQuery;

    private final CategoriaGastoRepository categoriaRepository;
    private final CategoriaGastoApplicationMapper categoriaMapper;
    
    private final UsuarioAutenticado usuarioAutenticado;

    public CategoriaGastoResponse ejecutar(
    		Long categoriaId,
    		String nombre
    		) {
    	
    	Long usuarioId = usuarioAutenticado.obtenerId();

    	CategoriaGasto categoria = categoriaQuery.obtenerPorId(categoriaId, usuarioId);
    	
    	if(categoria.esTipoSistema()) {
    		throw new ConflictException("No se puede modificar una categoria del sistema");
    	}

    	categoria.renombrar(nombre);
    	
    	CategoriaGasto guardada = categoriaRepository.guardar(categoria);
        
        log.info("Categoria Gasto editado correctamente");
        
        return categoriaMapper.mapToResponse(guardada);
    }
}