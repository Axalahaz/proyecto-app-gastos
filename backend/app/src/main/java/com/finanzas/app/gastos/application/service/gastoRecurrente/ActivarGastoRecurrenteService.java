package com.finanzas.app.gastos.application.service.gastoRecurrente;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.gastos.domain.entity.GastoRecurrente;
import com.finanzas.app.gastos.domain.repository.gastoRecurrente.GastoRecurrenteRepository;
import com.finanzas.app.shared.domain.vo.Fecha;
import com.finanzas.app.shared.exception.extend.NotFoundException;

import lombok.RequiredArgsConstructor;

@Transactional
@Service
@RequiredArgsConstructor
public class ActivarGastoRecurrenteService {

    private final GastoRecurrenteRepository gastoRecurrenteRepository;

    public void ejecutar(Long gastoRecurrenteId) {

        GastoRecurrente gastoRecurrente = gastoRecurrenteRepository
                .buscar(gastoRecurrenteId)
                .orElseThrow(() ->
                        NotFoundException.of("Gasto Recurrente", gastoRecurrenteId));

        Fecha fechaCambioActivo = new Fecha(LocalDateTime.now());
        
        gastoRecurrente.activar(fechaCambioActivo);

        // JPA lo detecta como UPDATE porque ya existe
        gastoRecurrenteRepository.guardar(gastoRecurrente);
    }
}