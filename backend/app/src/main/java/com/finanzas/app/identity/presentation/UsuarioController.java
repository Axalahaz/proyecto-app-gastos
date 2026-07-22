package com.finanzas.app.identity.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.finanzas.app.identity.application.service.usuario.EliminarUsuarioService;
import com.finanzas.app.identity.application.service.usuario.ModificarUsuarioService;
import com.finanzas.app.identity.application.service.usuario.ObtenerUsuarioActualService;
import com.finanzas.app.identity.application.service.usuario.password.CambiarPasswordService;
import com.finanzas.app.identity.presentation.dto.CambiarPasswordRequest;
import com.finanzas.app.identity.presentation.dto.ModificarUsuarioRequest;
import com.finanzas.app.identity.presentation.dto.UsuarioResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    private final ModificarUsuarioService modificarUsuarioService;
    private final CambiarPasswordService cambiarPasswordService;
    private final ObtenerUsuarioActualService obtenerUsuarioActualService;
    private final EliminarUsuarioService eliminarUsuarioService;

    // ----------------------------------------------------
    // ME
    
    @GetMapping("/me")
    public ResponseEntity<UsuarioResponse> obtenerUsuarioActual() {
    	
    	UsuarioResponse usuario = obtenerUsuarioActualService.ejecutar();
    	
    	return ResponseEntity.ok(usuario);
    }
    
    // ----------------------------------------------------
    // ACTUALIZAR
    
    @PatchMapping("/me")
    public ResponseEntity<Void> actualizarUsuario(
            @Valid @RequestBody ModificarUsuarioRequest request) {

        modificarUsuarioService.ejecutar(
                request.getEmail()
        );

        return ResponseEntity.noContent().build();
    }
    
    // ----------------------------------------------------
    // CAMBIAR CONTRASEÑA
    
    @PatchMapping("/me/contraseña")
    public ResponseEntity<Void> cambiarPasswordUsuario(
            @Valid @RequestBody CambiarPasswordRequest request) {

    	cambiarPasswordService.ejecutar(
                request.getPasswordActual(),
                request.getNuevaPassword()
        );

        return ResponseEntity.noContent().build();
    }

    // ----------------------------------------------------
    // ELIMINAR (borrado fisico)
    
    @DeleteMapping("/me")
    public ResponseEntity<Void> eliminarUsuario() {
    	
    	eliminarUsuarioService.ejecutar();
    	
    	return ResponseEntity.noContent().build();
    }
}