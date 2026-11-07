package com.finanzas.app.gastos.recurrentes.application.service.read;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.gastos.recurrentes.application.ConfGastoRecurrenteApplicationMapper;
import com.finanzas.app.gastos.recurrentes.domain.ConfGastoRecurrente;
import com.finanzas.app.gastos.recurrentes.domain.ConfGastoRecurrenteRepository;
import com.finanzas.app.gastos.recurrentes.presentation.dto.ConfGastoRecurrenteResponse;
import com.finanzas.app.shared.application.security.UsuarioAutenticado;

import lombok.RequiredArgsConstructor;

/*
 * Lista de gastos activos y anulados
 * 
 * */
@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class ListarTodasConfGastosRecurrentesService {

	private final ConfGastoRecurrenteRepository confGastoRecurrenteRepository;
    private final ConfGastoRecurrenteApplicationMapper confGastoRecurrenteApplicationMapper;
    
    private final UsuarioAutenticado usuarioAutenticado;

    public List<ConfGastoRecurrenteResponse> ejecutar() {
    	
    	Long usuarioId = usuarioAutenticado.obtenerId();

        List<ConfGastoRecurrente> lista = confGastoRecurrenteRepository.listarTodos(usuarioId);
        		
        return lista.stream()
                .map(confGastoRecurrenteApplicationMapper::mapToResponse)
                .toList();
    }
}