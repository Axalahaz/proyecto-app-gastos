package com.finanzas.app.gastos.gastos.application.service.write;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.gastos.gastos.application.GastoApplicationQuery;
import com.finanzas.app.gastos.gastos.domain.Gasto;
import com.finanzas.app.gastos.gastos.domain.GastoRepository;
import com.finanzas.app.shared.application.security.UsuarioAutenticado;
import com.finanzas.app.shared.exception.exceptionImpl.NotFoundException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/*
 * Permite eliminar fisicamente un error durante un tiempo limitado
 * Despues solo se puede anular
 * */
@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class EliminarGastoCommandService {

	private final GastoApplicationQuery gastoApplicationQuery;
    private final GastoRepository gastoRepository;
    
    private final UsuarioAutenticado usuarioAutenticado;

    public void ejecutar(Long gastoId) {

    	Long usuarioId = usuarioAutenticado.obtenerId();
    	
        Gasto gasto = gastoApplicationQuery.obtenerPorId(gastoId, usuarioId);
        
        LocalDateTime tiempoLimite = LocalDateTime.now();
        
        gasto.validarEliminacion(tiempoLimite);

        int filasEliminadas = gastoRepository.eliminar(gasto.getId(), usuarioId);
        
        if (filasEliminadas == 0) {
        	throw NotFoundException.of("No se encontro Gasto para eliminar");
        }
        
        log.info("Gasto {} eliminado correctamente", gasto.getId());
    }
}