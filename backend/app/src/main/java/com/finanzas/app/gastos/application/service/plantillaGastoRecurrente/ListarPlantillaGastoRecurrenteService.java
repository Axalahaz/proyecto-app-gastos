package com.finanzas.app.gastos.application.service.plantillaGastoRecurrente;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.gastos.application.mapper.PlantillaGastoRecurrenteApplicationMapper;
import com.finanzas.app.gastos.domain.entity.PlantillaGastoRecurrente;
import com.finanzas.app.gastos.domain.repository.plantillaGastoRecurrente.PlantillaGastoRecurrenteRepository;
import com.finanzas.app.gastos.presentation.admin.dto.plantillaGastoRecurrente.PlantillaGastoRecurrenteResponse;

import lombok.RequiredArgsConstructor;

/*
 * Lista de gastos activos y anulados
 * 
 * */
@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class ListarPlantillaGastoRecurrenteService {

	private final PlantillaGastoRecurrenteApplicationMapper mapper;
    private final PlantillaGastoRecurrenteRepository plantillaRepository;


    public List<PlantillaGastoRecurrenteResponse> ejecutar() {

        List<PlantillaGastoRecurrente> lista = plantillaRepository.listar();
        		
        return lista.stream()
                .map(mapper::mapToResponse)
                .toList();
    }
}