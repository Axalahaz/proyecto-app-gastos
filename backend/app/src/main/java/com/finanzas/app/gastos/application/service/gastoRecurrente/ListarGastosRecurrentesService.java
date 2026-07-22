package com.finanzas.app.gastos.application.service.gastoRecurrente;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.gastos.application.FiltroGastoRecurrente;
import com.finanzas.app.gastos.application.mapper.GastoRecurrenteApplicationMapper;
import com.finanzas.app.gastos.domain.entity.GastoRecurrente;
import com.finanzas.app.gastos.domain.repository.gastoRecurrente.GastoRecurrenteRepository;
import com.finanzas.app.gastos.presentation.user.dto.gastoRecurrente.GastoRecurrenteResponse;
import com.finanzas.app.shared.domain.UsuarioAutenticado;

import lombok.RequiredArgsConstructor;

/*
 * Lista de gastos activos y anulados
 * 
 * */
@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class ListarGastosRecurrentesService {

    private final GastoRecurrenteRepository gastoRecurrenteRepository;
    private final UsuarioAutenticado usuarioAutenticado;
    private final GastoRecurrenteApplicationMapper mapper;

    public List<GastoRecurrenteResponse> ejecutar(FiltroGastoRecurrente filtro) {

        Long userId = usuarioAutenticado.obtenerId();

        List<GastoRecurrente> lista = gastoRecurrenteRepository.listarPorEstado(userId);
        		
        switch (filtro) {
	        case ACTIVOS ->
	        	lista = lista.stream()
	        			.filter(GastoRecurrente::estaActiva)
	        			.toList();
	        case INACTIVOS ->
		        lista = lista.stream()
						.filter(gastoRecurrente -> !gastoRecurrente.estaActiva())
						.toList();
		    default -> {}
		};
        
        return lista.stream()
                .map(mapper::mapToResponse)
                .toList();
    }
}