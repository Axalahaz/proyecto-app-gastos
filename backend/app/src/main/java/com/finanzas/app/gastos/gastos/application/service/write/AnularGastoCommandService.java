package com.finanzas.app.gastos.gastos.application.service.write;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.gastos.gastos.application.GastoApplicationQuery;
import com.finanzas.app.gastos.gastos.domain.Gasto;
import com.finanzas.app.gastos.gastos.domain.GastoRepository;
import com.finanzas.app.shared.application.security.UsuarioAutenticado;
import com.finanzas.app.shared.domain.vo.Fecha;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class AnularGastoCommandService {

	private final GastoApplicationQuery gastoApplicationQuery;
    private final GastoRepository gastoRepository;

    private final UsuarioAutenticado usuarioAutenticado;
    
    public void ejecutar(Long gastoId) {

    	Long usuarioId = usuarioAutenticado.obtenerId();
    	
        Gasto gasto = gastoApplicationQuery.obtenerPorId(gastoId, usuarioId);

        Fecha fechaCambioEstado = new Fecha(LocalDateTime.now());
        
        gasto.anular(fechaCambioEstado);

        gastoRepository.guardar(gasto);
        
        log.info("Gasto {} anulado correctamente", gasto.getId());
    }
}