package com.finanzas.app.gastos.recurrentes.application.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.gastos.presentation.dto.gastoRecurrente.GastoRecurrenteResponse;
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
public class ListarTodosGastosRecurrentesService {

    private final GastoRecurrenteRepository gastoRecurrenteRepository;
    private final GastoRecurrenteApplicationMapper mapper;

    public List<GastoRecurrenteResponse> ejecutar() {

        List<GastoRecurrente> lista = gastoRecurrenteRepository.listarAll();
        		
        return lista.stream()
                .map(mapper::mapToResponse)
                .toList();
    }
}