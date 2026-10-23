package com.finanzas.app.gastos.recurrentes.application.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.gastos.presentation.dto.gastoRecurrente.GastoRecurrenteResponse;
import com.finanzas.app.gastos.recurrentes.application.FiltroGastoRecurrente;
import com.finanzas.app.gastos.recurrentes.application.GastoRecurrenteApplicationMapper;
import com.finanzas.app.gastos.recurrentes.domain.GastoRecurrente;
import com.finanzas.app.gastos.recurrentes.domain.GastoRecurrenteRepository;

import lombok.RequiredArgsConstructor;

/*
 * Lista de gastos activos y anulados
 * 
 * */
@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class ListarGastosRecurrentesService {

    private final GastoRecurrenteRepository repository;
    private final GastoRecurrenteApplicationMapper mapper;

    public List<GastoRecurrenteResponse> ejecutar(FiltroGastoRecurrente filter) {

        List<GastoRecurrente> lista = switch (filter) {
	        case ACTIVOS -> repository.listarPorEstado(true);
	        case INACTIVOS -> repository.listarPorEstado(false);
	        case TODOS -> repository.listarAll();
	    };
	        		
        return lista.stream()
                .map(mapper::mapToResponse)
                .toList();
    }
}