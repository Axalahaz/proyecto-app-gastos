package com.finanzas.app.gastos.gastos.application;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.gastos.gastos.application.contract.GastoApplication;
import com.finanzas.app.gastos.gastos.application.contract.GastoData;
import com.finanzas.app.gastos.gastos.domain.Gasto;
import com.finanzas.app.gastos.gastos.domain.GastoRepository;

import lombok.RequiredArgsConstructor;

@Transactional(readOnly  = true)
@Component
@RequiredArgsConstructor
public class GastoApplicationService implements GastoApplication {

	private final GastoApplicationQuery gastoApplicationQuery;
	private final GastoRepository gastoRepository;
	
	@Override
	public boolean existePorConfGastoRecurrenteId(Long confGastoRecurrenteId, Long usuarioId) {
		return gastoRepository.existePorConfGastoRecurrenteId(confGastoRecurrenteId, usuarioId);
	}

	@Override
	public GastoData obtenerDatos(Long gastoId, Long usuarioId) {
		Gasto gasto = gastoApplicationQuery.obtenerPorId(gastoId, usuarioId);
		
		return new GastoData(
				gasto.getId(),
				gasto.getUsuarioId(),
				gasto.getDescripcion(),
				gasto.getCategoriaGastoId(),
				gasto.getConfGastoRecurrenteId()
				);
	}
}