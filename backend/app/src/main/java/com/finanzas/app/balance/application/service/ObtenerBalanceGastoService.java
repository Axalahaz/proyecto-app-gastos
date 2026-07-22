package com.finanzas.app.balance.application.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.balance.application.dto.gasto.BalanceGastosResponse;
import com.finanzas.app.balance.application.dto.gasto.GastoTotalPorCategoriaDto;
import com.finanzas.app.balance.application.filtrosEnum.FiltroEstadoMovimiento;
import com.finanzas.app.balance.application.filtrosEnum.FiltroTipoFechaBalance;
import com.finanzas.app.balance.domain.repository.gastos.BalanceGastoQueryRepository;
import com.finanzas.app.shared.domain.UsuarioAutenticado;
import com.finanzas.app.shared.domain.model.EstadoMovimiento;
import com.finanzas.app.shared.exception.extend.ValidationException;

import lombok.RequiredArgsConstructor;

/*
 * BALANCE:
 * - total del dia
 * - lista de gastos con sus totales
 * 
 * */

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class ObtenerBalanceGastoService {

    private final BalanceGastoQueryRepository repository;
    private final UsuarioAutenticado usuarioAutenticado;

    public BalanceGastosResponse ejecutar(
    		LocalDate fecha, 
    		FiltroTipoFechaBalance filtroFecha,
    		FiltroEstadoMovimiento filtroEstado
    		) {

        Long userId = usuarioAutenticado.obtenerId();
        
        LocalDateTime fechaInicio;
        LocalDateTime fechaFin;
        
        switch (filtroFecha) {
			case DIARIO -> {
				fechaInicio = fecha.atStartOfDay(); // 2026-06-03T00:00:00
				fechaFin = fecha.plusDays(1).atStartOfDay(); // 2026-06-04T00:00:00
			}
			case MENSUAL -> {
				fechaInicio = fecha.withDayOfMonth(1).atStartOfDay();
				fechaFin = fecha.withDayOfMonth(1).plusMonths(1).atStartOfDay();
			}
			case ANUAL -> {
				fechaInicio = fecha.withDayOfYear(1).atStartOfDay();
				fechaFin = fecha.withDayOfYear(1).plusYears(1).atStartOfDay();
			}
			default -> { throw ValidationException.of("Filtro de tipo de fecha invalido"); }
		}
        
        List<EstadoMovimiento> estados = filtroEstado.obtenerEstados();
        
        BigDecimal total = repository.obtenerTotal(userId, fechaInicio, fechaFin, estados);
        
        List<GastoTotalPorCategoriaDto> porCategoria =
                repository.obtenerTotalPorCategoria(userId, fechaInicio, fechaFin, estados);

        return BalanceGastosResponse.of(total, porCategoria);
    }
}