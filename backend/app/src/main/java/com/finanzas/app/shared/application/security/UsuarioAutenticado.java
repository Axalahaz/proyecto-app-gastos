package com.finanzas.app.shared.application.security;

// el dominio pide “quién es el usuario actual”

public interface UsuarioAutenticado {
	Long obtenerId();
}