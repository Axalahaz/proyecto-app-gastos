package com.finanzas.app.gastos.gastos.application.service.write;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.gastos.categorias.application.contract.CategoriaGastoApplication;
import com.finanzas.app.gastos.gastos.application.GastoApplicationMapper;
import com.finanzas.app.gastos.gastos.application.GastoApplicationQuery;
import com.finanzas.app.gastos.gastos.presentation.dto.GastoResponse;
import com.finanzas.app.gastos.gastos.domain.Gasto;
import com.finanzas.app.gastos.gastos.domain.GastoRepository;
import com.finanzas.app.shared.application.security.UsuarioAutenticado;
import com.finanzas.app.shared.domain.vo.CategoriaId;
import com.finanzas.app.shared.domain.vo.Descripcion;
import com.finanzas.app.shared.domain.vo.Money;

import lombok.RequiredArgsConstructor;

@Transactional
@Service
@RequiredArgsConstructor
public class EditarGastoCommandService {

	private final CategoriaGastoApplication categoriaGastoApplication;

	private final GastoApplicationQuery gastoApplicationQuery;
    private final GastoRepository gastoRepository;
    private final GastoApplicationMapper gastoApplicationMapper;
    
    private final UsuarioAutenticado usuarioAutenticado;
    
    public GastoResponse ejecutar(
            Long gastoId,
            BigDecimal importe,
            String descripcion,
            Long nuevaCategoriaId
    		) {

    	Long usuarioId = usuarioAutenticado.obtenerId();
    	
        Gasto gasto = gastoApplicationQuery.obtenerPorId(gastoId, usuarioId);
        
        gasto.validarEstadoActivo();
        
        // --------------------------
        // Comprovar validez de datos
        
        CategoriaId categoriaIdVO = null;
        if (nuevaCategoriaId != null) {
            categoriaGastoApplication.existePorId(nuevaCategoriaId, usuarioId);
            categoriaIdVO = new CategoriaId(nuevaCategoriaId);
        }
        
        Money moneyVO = importe != null 
        		? new Money(importe) 
        		: null;
        
        Descripcion descripcionVO = descripcion != null 
        		? new Descripcion(descripcion) 
        		: null;
        
        // --------------------------
        
        gasto.editar(
        		categoriaIdVO,
                moneyVO,
                descripcionVO
        );
        
        Gasto actualizado = gastoRepository.guardar(gasto);

        return gastoApplicationMapper.mapToResponse(actualizado);
    }
}