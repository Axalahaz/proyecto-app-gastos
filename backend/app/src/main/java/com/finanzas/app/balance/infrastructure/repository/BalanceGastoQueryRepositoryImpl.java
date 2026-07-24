package com.finanzas.app.balance.infrastructure.repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.finanzas.app.balance.application.dto.gasto.GastoRecurrentesPagosDto;
import com.finanzas.app.balance.application.dto.gasto.GastoTotalPorCategoriaDto;
import com.finanzas.app.balance.domain.repository.gastos.BalanceGastoQueryRepository;
import com.finanzas.app.gastos.infrastructure.respository.gasto.GastoQueryRepositoryJPA;
import com.finanzas.app.shared.domain.model.EstadoMovimiento;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class BalanceGastoQueryRepositoryImpl implements BalanceGastoQueryRepository {

    private final GastoQueryRepositoryJPA gastoJPA;

    @Override
    public BigDecimal obtenerTotal(
    		LocalDateTime fechaInicio, 
    		LocalDateTime fechaFin, 
    		List<EstadoMovimiento> estados
    ) {
        return gastoJPA.consultarTotalGastos(fechaInicio, fechaFin, estados);
    }

	@Override
	public List<GastoTotalPorCategoriaDto> obtenerTotalPorCategoria(
			LocalDateTime fechaInicio, 
			LocalDateTime fechaFin, 
			List<EstadoMovimiento> estados
	) {
		
		return gastoJPA.consultarTotalesPorCategoria(fechaInicio, fechaFin, estados)
	            .stream()
	            .map(obj -> GastoTotalPorCategoriaDto.of(
	                    (String) obj[0],
	                    (BigDecimal) obj[1]
	            ))
	            .toList();
	}

	@Override
	public BigDecimal obtenerTotalPagos(
			LocalDateTime fechaInicio, 
			LocalDateTime fechaFin,
			List<EstadoMovimiento> estados, 
			List<Boolean> recurrente
	) {
		return gastoJPA.consultarTotalPagos(fechaInicio, fechaFin, estados, recurrente);
	}

	@Override
	public List<GastoRecurrentesPagosDto> obtenerDetallePagosRealizados(
			LocalDateTime fechaInicio,
			LocalDateTime fechaFin, 
			List<EstadoMovimiento> estados, 
			List<Boolean> recurrente
	) {
		return gastoJPA.consultarDetallePagosRealizados(fechaInicio, fechaFin, estados, recurrente)
	            .stream()
	            .map(obj -> GastoRecurrentesPagosDto.of(
	                    (String) obj[0],
	                    (BigDecimal) obj[1],
	                    (String) obj[2],
	                    (LocalDateTime) obj[3]
	            ))
	            .toList();
	}

}