package com.finanzas.app.identity.presentation;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.finanzas.app.identity.application.service.auth.LoginUsuarioService;
import com.finanzas.app.identity.application.service.usuario.RegistrarUsuarioService;
import com.finanzas.app.identity.application.service.usuario.password.ResetearPasswordService;
import com.finanzas.app.identity.application.service.usuario.password.SolicitarRecuperacionPasswordService;

import com.finanzas.app.identity.domain.entity.Usuario;
import com.finanzas.app.identity.presentation.dto.LoginRequest;
import com.finanzas.app.identity.presentation.dto.LoginResponse;
import com.finanzas.app.identity.presentation.dto.RecuperarPasswordRequest;
import com.finanzas.app.identity.presentation.dto.RegistrarUsuarioRequest;
import com.finanzas.app.identity.presentation.dto.ResetPasswordRequest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final RegistrarUsuarioService registrarUsuarioService;
    private final LoginUsuarioService loginUsuarioService;
    private final SolicitarRecuperacionPasswordService solicitarRecuperacionPasswordService;
    private final ResetearPasswordService resetearPasswordService;

    // ----------------------------------------------------
    // REGISTRO
    
    @PostMapping("/registrar")
    public ResponseEntity<Map<String,String>> register(
    		@Valid @RequestBody RegistrarUsuarioRequest request) {
    	
    	Usuario usuario = registrarUsuarioService.ejecutar(
                request.getEmail(),
                request.getPassword()
        );
        return ResponseEntity
    			.status(HttpStatus.CREATED)
    			.body(
    					Map.of("Email", usuario.getEmail())
    			);
    }
    
    // ----------------------------------------------------
    // LOGIN

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @Valid @RequestBody LoginRequest request) {

        String token = loginUsuarioService.ejecutar(
                request.getEmail(),
                request.getPassword()
        );

        return ResponseEntity.ok(new LoginResponse(token));
    }
    
    // ----------------------------------------------------
    // RECUPERAR
    
    // 1ero
    
    @PostMapping("/password/recuperar")
    public ResponseEntity<Map<String, String>> recuperarPassword(
    		@Valid @RequestBody RecuperarPasswordRequest request) {
    	
    	String token = solicitarRecuperacionPasswordService.ejecutar(request.getEmail());
    	
    	return ResponseEntity
    			.ok(Map.of("tokenReset", token));
    }
    
    // 2do
    
    @PostMapping("/password/recuperar/resetear")
    public ResponseEntity<Void> resetearPassword(
    		@Valid @RequestBody ResetPasswordRequest request) {
    	
    	resetearPasswordService.ejecutar(request.getPassword(), request.getToken());
    	
    	return ResponseEntity.noContent().build();
    }
    
    
    // ----------------------------------------------------
    // TEST
    
    @GetMapping("/test")
    public String test() {
        return "OK";
    }
}