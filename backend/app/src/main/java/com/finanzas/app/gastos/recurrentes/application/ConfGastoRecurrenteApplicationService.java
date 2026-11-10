package com.finanzas.app.gastos.recurrentes.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.gastos.recurrentes.application.contract.ConfGastoRecurrenteApplication;
import com.finanzas.app.gastos.recurrentes.application.contract.ConfGastoRecurrenteData;
import com.finanzas.app.gastos.recurrentes.application.service.write.EliminarTodosPorUsuarioConfGastoRecurrenteCommandService;
import com.finanzas.app.gastos.recurrentes.domain.ConfGastoRecurrente;
import com.finanzas.app.gastos.recurrentes.domain.ConfGastoRecurrenteRepository;
import com.finanzas.app.shared.domain.enums.Frecuencia;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ConfGastoRecurrenteApplicationService implements ConfGastoRecurrenteApplication {

	private final ConfGastoRecurrenteApplicationQuery confGastoRecurrenteApplicationQuery;
    private final ConfGastoRecurrenteRepository repository;
    private final ConfGastoRecurrenteFactory confGastoRecurrenteFactory;
    
	private final EliminarTodosPorUsuarioConfGastoRecurrenteCommandService eliminarDatosDeUsuarioService;

    @Override
    @Transactional(readOnly = true)
    public boolean existePorDescripcion(
        String descripcion,
        Long usuarioId
    ) {
        return repository.existePorDescripcion(descripcion, usuarioId);
    }

    @Override
    public Long crearDesdeGasto(
        Long usuarioId,
        String descripcion,
        Long categoriaGastoId,
        Frecuencia frecuencia,
        Integer diaVencimiento,
        Integer mesVencimiento
    ) {

    	ConfGastoRecurrente confGastoRecurrente = confGastoRecurrenteFactory.crear(
        		usuarioId,
        		descripcion,
        		categoriaGastoId,
     		    frecuencia,
     		    diaVencimiento,
    		   	mesVencimiento
        );
    	
    	ConfGastoRecurrente guardado = repository.guardar(confGastoRecurrente);
    	
        return guardado.getId();
    }
    
    @Override
    public ConfGastoRecurrenteData obtenerDatos(
        Long confGastoRecurrenteId,
        Long usuarioId
    ) {

    	ConfGastoRecurrente confGastoRecurrente = confGastoRecurrenteApplicationQuery
        		.obtenerPorId(confGastoRecurrenteId, usuarioId);

        confGastoRecurrente.validarConfRecurrenciaEstaActiva();

        return new ConfGastoRecurrenteData(
            confGastoRecurrente.getId(),
            confGastoRecurrente.getUsuarioId(),
            confGastoRecurrente.getDescripcion(),
            confGastoRecurrente.getCategoriaGastoId(),
            confGastoRecurrente.getPeriodicidad().getFrecuencia(),
            confGastoRecurrente.getPeriodicidad().getDiaVencimiento(),
            confGastoRecurrente.getPeriodicidad().getMesVencimiento(),
            confGastoRecurrente.getEstado()
        );
    }
    
    @Override
	@Transactional
	public void eliminarDatosDeUsuario(Long usuarioId) {
		eliminarDatosDeUsuarioService.ejecutar(usuarioId);
	}
}