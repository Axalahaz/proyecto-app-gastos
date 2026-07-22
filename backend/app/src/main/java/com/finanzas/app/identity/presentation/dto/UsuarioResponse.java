package com.finanzas.app.identity.presentation.dto;

import com.finanzas.app.shared.domain.vo.Fecha;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UsuarioResponse {
	
	private Long id;
    private String email;
    private Boolean activo;
    private Fecha fechaCreacion;
    private Fecha fechaModificacion;
    private Fecha fechaCambioEstado;
    private Fecha fechaCambioPassword;
	
}