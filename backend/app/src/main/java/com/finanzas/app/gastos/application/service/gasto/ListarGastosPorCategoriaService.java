package com.finanzas.app.gastos.application.service.gasto;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.gastos.application.mapper.GastoApplicationMapper;
import com.finanzas.app.gastos.domain.entity.Gasto;
import com.finanzas.app.gastos.domain.repository.categoriaGasto.CategoriaGastoRepository;
import com.finanzas.app.gastos.domain.repository.gasto.GastoRepository;
import com.finanzas.app.gastos.presentation.user.dto.gasto.GastoResponse;
import com.finanzas.app.shared.domain.UsuarioAutenticado;
import com.finanzas.app.shared.exception.extend.NotFoundException;

import lombok.RequiredArgsConstructor;

/*
 * Lista de gastos activos y anulados
 * 
 * */
@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class ListarGastosPorCategoriaService {

    private final CategoriaGastoRepository categoriaRepository;
    private final GastoRepository gastoRepository;
    private final UsuarioAutenticado usuarioAutenticado;
    private final GastoApplicationMapper mapper;

    public List<GastoResponse> ejecutar(Long categoriaGastoId) {

        Long userId = usuarioAutenticado.obtenerId();

        // control de consistencia
        categoriaRepository
                .buscarDisponible(categoriaGastoId, userId)
                .orElseThrow(() -> NotFoundException.of("Categoría", categoriaGastoId));
        
        List<Gasto> lista = gastoRepository.listarPorCategoriaYUsuario(categoriaGastoId, userId);

        return lista.stream()
                .map(mapper::mapToResponse)
                .toList();
    }
}