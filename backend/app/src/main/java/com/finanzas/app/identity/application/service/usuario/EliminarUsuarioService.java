package com.finanzas.app.identity.application.service.usuario;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.gastos.categorias.application.contract.CategoriaGastoApplication;
import com.finanzas.app.gastos.gastos.application.contract.GastoApplication;
import com.finanzas.app.gastos.recurrentes.application.contract.ConfGastoRecurrenteApplication;
import com.finanzas.app.identity.domain.UsuarioRepository;
import com.finanzas.app.shared.application.security.UsuarioAutenticado;
import com.finanzas.app.shared.exception.exceptionImpl.NotFoundException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class EliminarUsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioAutenticado usuarioAutenticado;
    
    private final GastoApplication gastoApplication;
	private final CategoriaGastoApplication categoriaApplication;
	private final ConfGastoRecurrenteApplication confGastoRecurrenteApplication;

    public void ejecutar() {

        Long usuarioId = usuarioAutenticado.obtenerId();
        
        log.info("Iniciando eliminación del usuario {}", usuarioId);
        
        // Eliminar información de los componentes.
        gastoApplication.eliminarDatosDeUsuario(usuarioId);
        log.info("Gastos eliminados.");
        
        confGastoRecurrenteApplication.eliminarDatosDeUsuario(usuarioId);
        log.info("Configuraciones recurrentes eliminadas.");
        
        categoriaApplication.eliminarDatosDeUsuario(usuarioId);
        log.info("Categorías eliminadas.");

        int filasEliminadas = usuarioRepository.eliminar(usuarioId);
        
        if(filasEliminadas == 0) {
    		throw NotFoundException.of("No se pudo eliminar el Usuario");
    	}
        
        log.info("Usuario {} eliminado correctamente", usuarioId);
    }
}