package com.finanzas.app.balance.application.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.balance.application.dto.gasto.BalanceGastosRecurrentesResponse;
import com.finanzas.app.balance.application.dto.gasto.GastoRecurrentesPagosDto;
import com.finanzas.app.balance.application.filtrosEnum.FiltroEstadoMovimiento;
import com.finanzas.app.balance.application.filtrosEnum.FiltroEstadoRecurrente;
import com.finanzas.app.balance.application.filtrosEnum.FiltroFrecuenciaGastoRecurrente;
import com.finanzas.app.balance.domain.repository.gastos.BalanceGastoQueryRepository;
import com.finanzas.app.shared.domain.UsuarioAutenticado;
import com.finanzas.app.shared.domain.model.EstadoMovimiento;
import com.finanzas.app.shared.exception.extend.ValidationException;

import lombok.RequiredArgsConstructor;


@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class ObtenerBalanceGastoRecurrenteService {

    private final BalanceGastoQueryRepository repository;
    private final UsuarioAutenticado usuarioAutenticado;

    public BalanceGastosRecurrentesResponse ejecutar(
    		LocalDate fecha, 
    		FiltroFrecuenciaGastoRecurrente filtroFecha,
    		FiltroEstadoMovimiento filtroEstado,
    		FiltroEstadoRecurrente filtroRecurrente
    		) {

        Long userId = usuarioAutenticado.obtenerId();
        
        LocalDateTime fechaInicio;
        LocalDateTime fechaFin;
        
        switch (filtroFecha) {
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

        List<Boolean> recurrente = filtroRecurrente.obtenerEstados();
        
        BigDecimal total = repository.obtenerTotalPagos(userId, fechaInicio, fechaFin, estados, recurrente);
        
        List<GastoRecurrentesPagosDto> porPagos =
                repository.obtenerDetallePagosRealizados(userId, fechaInicio, fechaFin, estados, recurrente);

        return BalanceGastosRecurrentesResponse.of(total, porPagos);
    }
}