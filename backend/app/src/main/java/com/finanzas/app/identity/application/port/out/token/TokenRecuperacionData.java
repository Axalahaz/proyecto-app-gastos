package com.finanzas.app.identity.application.port.out.token;

public record TokenRecuperacionData(
	    Long usuarioId,
	    Integer resetVersion
	) {}