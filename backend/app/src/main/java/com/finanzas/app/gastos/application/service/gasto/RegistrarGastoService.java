package com.finanzas.app.gastos.application.service.gasto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.gastos.application.mapper.GastoApplicationMapper;
import com.finanzas.app.gastos.domain.entity.Gasto;
import com.finanzas.app.gastos.domain.repository.categoriaGasto.CategoriaGastoRepository;
import com.finanzas.app.gastos.domain.repository.gasto.GastoRepository;
import com.finanzas.app.gastos.presentation.user.dto.gasto.GastoResponse;
import com.finanzas.app.shared.domain.UsuarioAutenticado;
import com.finanzas.app.shared.domain.vo.Fecha;
import com.finanzas.app.shared.domain.vo.Money;
import com.finanzas.app.shared.exception.extend.NotFoundException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/*
 *  -------------------------------
 *  Registro de gasto comun desde una categoria
 *  -------------------------------
 * */
@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class RegistrarGastoService {

    private final CategoriaGastoRepository categoriaRepository;
    private final GastoRepository gastoRepository;
    private final UsuarioAutenticado usuarioAutenticado;
    private final GastoApplicationMapper gastoApplicationMapper;

    
    public GastoResponse ejecutar(
    		BigDecimal monto, 
    		String descripcion, 
    		Long categoriaGastoId
    		) {

    	Long userId = usuarioAutenticado.obtenerId();
    	
    	// control de consistencia
        boolean existe = categoriaRepository
                .existePorIdYUsuarioId(categoriaGastoId, userId);
        
        if (!existe) throw NotFoundException.of("Categoría", categoriaGastoId);
        
        Money money = new Money(monto);
        Fecha fechaCreacion = new Fecha(LocalDateTime.now());

        Gasto gasto = Gasto.crear(
        		userId,
        		categoriaGastoId,
        		null,
                money,
                descripcion,
                fechaCreacion
        );

        Gasto guardado = gastoRepository.guardar(gasto);
        
        log.info("Gasto {} registrado correctamente", gasto.getId());
        
        return gastoApplicationMapper.mapToResponse(guardado);
    }
}