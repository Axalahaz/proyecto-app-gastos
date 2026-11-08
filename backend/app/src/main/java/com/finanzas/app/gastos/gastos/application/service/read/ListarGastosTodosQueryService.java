package com.finanzas.app.gastos.gastos.application.service.read;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.gastos.gastos.application.GastoApplicationMapper;
import com.finanzas.app.gastos.gastos.domain.Gasto;
import com.finanzas.app.gastos.gastos.domain.GastoRepository;
import com.finanzas.app.gastos.gastos.presentation.dto.GastoResponse;
import com.finanzas.app.shared.application.security.UsuarioAutenticado;

import lombok.RequiredArgsConstructor;

/*
 * Lista de gastos activos y anulados
 * 
 * */
@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class ListarGastosTodosQueryService {

    private final GastoRepository gastoRepository;
    private final GastoApplicationMapper mapper;
    
    private final UsuarioAutenticado usuarioAutenticado;

    public List<GastoResponse> ejecutar() {
    	
    	Long usuarioId = usuarioAutenticado.obtenerId();
    	
        List<Gasto> lista = gastoRepository.listarTodos(usuarioId);

        return lista.stream()
                .map(mapper::mapToResponse)
                .toList();
    }
}