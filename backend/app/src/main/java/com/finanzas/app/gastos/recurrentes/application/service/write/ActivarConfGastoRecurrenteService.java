package com.finanzas.app.gastos.recurrentes.application.service.write;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.gastos.recurrentes.application.ConfGastoRecurrenteApplicationQuery;
import com.finanzas.app.gastos.recurrentes.domain.ConfGastoRecurrente;
import com.finanzas.app.gastos.recurrentes.domain.ConfGastoRecurrenteRepository;
import com.finanzas.app.shared.application.security.UsuarioAutenticado;
import com.finanzas.app.shared.domain.vo.Fecha;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class ActivarConfGastoRecurrenteService {

	private final ConfGastoRecurrenteApplicationQuery confGastoRecurrenteApplicationQuery;
    private final ConfGastoRecurrenteRepository confGastoRecurrenteRepository;

    private final UsuarioAutenticado usuarioAutenticado;
    
    public void ejecutar(Long gastoRecurrenteId) {
    	
    	Long usuarioId = usuarioAutenticado.obtenerId();

    	ConfGastoRecurrente confGastoRecurrente = confGastoRecurrenteApplicationQuery
        		.obtenerPorId(gastoRecurrenteId, usuarioId);

        Fecha fechaModificacion = new Fecha(LocalDateTime.now());
        
        confGastoRecurrente.activar(fechaModificacion);

        // JPA lo detecta como UPDATE porque ya existe
        confGastoRecurrenteRepository.guardar(confGastoRecurrente);
        
        log.info("Configuracion Gasto Recurrente {} activado correctamente", confGastoRecurrente.getId());
    }
}