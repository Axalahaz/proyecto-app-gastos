package com.finanzas.app.gastos.recurrentes.application.service.write;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.gastos.gastos.domain.GastoRepository;
import com.finanzas.app.gastos.recurrentes.application.ConfGastoRecurrenteApplicationQuery;
import com.finanzas.app.gastos.recurrentes.domain.ConfGastoRecurrente;
import com.finanzas.app.gastos.recurrentes.domain.ConfGastoRecurrenteRepository;
import com.finanzas.app.shared.application.security.UsuarioAutenticado;
import com.finanzas.app.shared.exception.exceptionImpl.ConflictException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class EliminarConfGastoRecurrenteService {
	
	private final ConfGastoRecurrenteApplicationQuery confGastoRecurrenteApplicationQuery;
    private final ConfGastoRecurrenteRepository confGastoRecurrenteRepository;
    private final GastoRepository gastoRepository;
    
    private final UsuarioAutenticado usuarioAutenticado;

    public void ejecutar(Long gastoRecurrenteId) {

    	Long usuarioId = usuarioAutenticado.obtenerId();
    	
        ConfGastoRecurrente confGastoRecurrente = confGastoRecurrenteApplicationQuery
        		.obtenerPorId(gastoRecurrenteId, usuarioId);
        
        // Validar que no paso el tiempo requerido desde la creacion para eliminar
        LocalDateTime tiempoLimite = LocalDateTime.now();
        confGastoRecurrente.validarEliminacion(tiempoLimite);

        //
        if (gastoRepository.existePorGastoRecurrenteId(confGastoRecurrente.getId())) {
            throw new ConflictException(
            	"La configuracion ya posee gastos asociados y solo puede desactivarse."
            );
        }
        
        confGastoRecurrenteRepository.eliminar(confGastoRecurrente.getId(), usuarioId);
        
        log.info("Configuracion Gasto Recurrente {} eliminado correctamente", confGastoRecurrente.getId());
    }
}