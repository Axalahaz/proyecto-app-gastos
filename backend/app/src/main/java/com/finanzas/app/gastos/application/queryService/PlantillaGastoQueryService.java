package com.finanzas.app.gastos.application.queryService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.gastos.domain.entity.PlantillaGasto;
import com.finanzas.app.gastos.domain.repository.plantillaGasto.PlantillaGastoRepository;
import com.finanzas.app.shared.exception.extend.NotFoundException;

import lombok.RequiredArgsConstructor;

@Transactional(readOnly  = true)
@RequiredArgsConstructor
@Service
public class PlantillaGastoQueryService {

    private final PlantillaGastoRepository plantillaRepository;

    public PlantillaGasto obtenerPorId(Long id) {
        return plantillaRepository.buscarPorId(id)
                .orElseThrow(() -> NotFoundException.of("Categoría", id));
    }
}