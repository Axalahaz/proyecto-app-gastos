package com.finanzas.app.gastos.application.service;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import com.finanzas.app.gastos.domain.repository.categoriaGasto.CategoriaGastoRepository;
import com.finanzas.app.gastos.domain.repository.gasto.GastoRepository;
import com.finanzas.app.gastos.domain.repository.gastoRecurrente.GastoRecurrenteRepository;
import com.finanzas.app.shared.events.UsuarioEliminadoEvent;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class UsuarioEliminadoListener {

    private final GastoRepository gastoRepository;
    private final GastoRecurrenteRepository gastoRecurrenteRepository;
    private final CategoriaGastoRepository cateogoriaGastoRepository;
    
    // Se ejecuta después de que la transacción del usuario haya hecho commit.
	// Si el commit falla, este listener no se ejecuta.
    @TransactionalEventListener(
            phase = TransactionPhase.AFTER_COMMIT
        )
    
    // Abre nueva transaccion
    @Transactional(
            propagation = Propagation.REQUIRES_NEW
        )

    public void manejar(UsuarioEliminadoEvent event) {

        try {
        	log.info("Inicio - limpieza de datos del usuario {}", event.usuarioId());
        	
			gastoRepository.eliminarTodos(event.usuarioId());
			gastoRecurrenteRepository.eliminarTodos(event.usuarioId());
			cateogoriaGastoRepository.eliminarTodos(event.usuarioId());

			log.info("Fin - Usuario {} eliminado correctamente", event.usuarioId());
			
		} catch (Exception e) {
	        log.error("Error eliminando datos del usuario {}", event.usuarioId(), e);
	        throw e;
	    }
    }
}
