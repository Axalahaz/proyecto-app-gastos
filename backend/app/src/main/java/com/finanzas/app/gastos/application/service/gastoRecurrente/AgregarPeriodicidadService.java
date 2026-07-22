package com.finanzas.app.gastos.application.service.gastoRecurrente;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.gastos.domain.entity.Frecuencia;
import com.finanzas.app.gastos.domain.entity.GastoRecurrente;
import com.finanzas.app.gastos.domain.repository.gastoRecurrente.GastoRecurrenteRepository;
import com.finanzas.app.shared.domain.UsuarioAutenticado;
import com.finanzas.app.shared.exception.extend.NotFoundException;

import lombok.RequiredArgsConstructor;

@Transactional
@Service
@RequiredArgsConstructor
public class AgregarPeriodicidadService {

    private final GastoRecurrenteRepository gastoRecurrenteRepository;
    private final UsuarioAutenticado usuarioAutenticado;

    public void ejecutar(
    		Long gastoRecurrenteId,
            Integer nuevoDiaVencimiento,
            Integer nuevoMesVencimiento,
            Frecuencia nuevaFrecuencia
    	) {

        Long userId = usuarioAutenticado.obtenerId();

        GastoRecurrente gastoRecurrente = gastoRecurrenteRepository
                .buscar(gastoRecurrenteId, userId)
                .orElseThrow(() ->
                        NotFoundException.of("Gasto Recurrente", gastoRecurrenteId));

        gastoRecurrente.agregarPeriodicidad(
        		nuevaFrecuencia,
    			nuevoDiaVencimiento,
    			nuevoMesVencimiento
        		);

        gastoRecurrenteRepository.guardar(gastoRecurrente);
    }
}