package com.finanzas.app.gastos.application.service.plantillaGasto;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.gastos.application.mapper.PlantillaGastoApplicationMapper;
import com.finanzas.app.gastos.domain.entity.PlantillaGasto;
import com.finanzas.app.gastos.domain.repository.plantillaGasto.PlantillaGastoRepository;
import com.finanzas.app.gastos.presentation.dto.plantillaGasto.PlantillaGastoResponse;

import lombok.RequiredArgsConstructor;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class ListarTodosPlantillaGastoService {

	private final PlantillaGastoRepository plantillaRepository;
	private final PlantillaGastoApplicationMapper mapper;

    public List<PlantillaGastoResponse> ejecutar() {

    	List<PlantillaGasto> lista = plantillaRepository.listarAll();

        return lista
        		.stream()
                .map(mapper::mapToResponse)
                .toList();
    }
}
