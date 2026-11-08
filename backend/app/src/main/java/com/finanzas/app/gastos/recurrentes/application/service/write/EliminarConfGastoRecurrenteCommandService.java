package com.finanzas.app.gastos.recurrentes.application.service.write;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.gastos.gastos.application.contract.GastoApplication;
import com.finanzas.app.gastos.recurrentes.application.ConfGastoRecurrenteApplicationQuery;
import com.finanzas.app.gastos.recurrentes.domain.ConfGastoRecurrente;
import com.finanzas.app.gastos.recurrentes.domain.ConfGastoRecurrenteRepository;
import com.finanzas.app.shared.application.security.UsuarioAutenticado;
import com.finanzas.app.shared.exception.exceptionImpl.ConflictException;
import com.finanzas.app.shared.exception.exceptionImpl.NotFoundException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class EliminarConfGastoRecurrenteCommandService {
	
	private final ConfGastoRecurrenteApplicationQuery confGastoRecurrenteApplicationQuery;
    private final ConfGastoRecurrenteRepository confGastoRecurrenteRepository;
    
    private final GastoApplication gastoApplication;
    
    private final UsuarioAutenticado usuarioAutenticado;

    public void ejecutar(Long gastoRecurrenteId) {

    	Long usuarioId = usuarioAutenticado.obtenerId();
    	
        ConfGastoRecurrente confGastoRecurrente = confGastoRecurrenteApplicationQuery
        		.obtenerPorId(gastoRecurrenteId, usuarioId);
        
        // Validar que no paso el tiempo requerido desde la creacion para eliminar
        LocalDateTime tiempoLimite = LocalDateTime.now();
        confGastoRecurrente.validarEliminacion(tiempoLimite);

        // Validar que no se asociaron gastos
        boolean existeGasto = gastoApplication
        		.existePorConfGastoRecurrenteId(confGastoRecurrente.getId(), usuarioId);
        
        if (existeGasto) {
            throw new ConflictException(
            	"La configuracion ya posee gastos asociados y solo puede desactivarse."
            );
        }
        
        int filasEliminadas = confGastoRecurrenteRepository.eliminar(confGastoRecurrente.getId(), usuarioId);
        if (filasEliminadas == 0) {
        	throw NotFoundException.of("No se encontro Configuracion Gasto Recurrente para eliminar");
        }
        
        log.info("Configuracion Gasto Recurrente {} eliminado correctamente", confGastoRecurrente.getId());
    }
}