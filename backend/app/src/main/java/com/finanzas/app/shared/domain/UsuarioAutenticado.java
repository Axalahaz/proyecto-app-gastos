package com.finanzas.app.shared.domain;

// el dominio pide “quién es el usuario actual”
// se usa en {@Link package com.finanzas.app.shared.infrastructure.security.adapter;}

public interface UsuarioAutenticado {
	Long obtenerId();
}