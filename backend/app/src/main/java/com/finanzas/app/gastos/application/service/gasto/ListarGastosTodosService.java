package com.finanzas.app.gastos.application.service.gasto;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.gastos.application.mapper.GastoApplicationMapper;
import com.finanzas.app.gastos.domain.entity.Gasto;
import com.finanzas.app.gastos.domain.repository.gasto.GastoRepository;
import com.finanzas.app.gastos.presentation.dto.gasto.GastoResponse;

import lombok.RequiredArgsConstructor;

/*
 * Lista de gastos activos y anulados
 * 
 * */
@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class ListarGastosTodosService {

    private final GastoRepository gastoRepository;
    private final GastoApplicationMapper mapper;

    public List<GastoResponse> ejecutar() {

        List<Gasto> lista = gastoRepository.listarTodos();

        return lista.stream()
                .map(mapper::mapToResponse)
                .toList();
    }
}