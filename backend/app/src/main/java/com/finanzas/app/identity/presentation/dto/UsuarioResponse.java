package com.finanzas.app.identity.presentation.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UsuarioResponse {
	
	private Long id;
	private String nombreUsuario;
    private String email;
    private Boolean activo;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaModificacion;
    private LocalDateTime fechaCambioPassword;
}