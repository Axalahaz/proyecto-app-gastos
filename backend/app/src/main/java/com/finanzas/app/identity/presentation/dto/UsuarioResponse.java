package com.finanzas.app.identity.presentation.dto;

import java.time.LocalDateTime;

public record UsuarioResponse (
	
	Long id,
	String nombreUsuario,
    String email,
    Boolean activo,
    LocalDateTime fechaCreacion,
    LocalDateTime fechaModificacion,
    LocalDateTime fechaCambioPassword
) {}