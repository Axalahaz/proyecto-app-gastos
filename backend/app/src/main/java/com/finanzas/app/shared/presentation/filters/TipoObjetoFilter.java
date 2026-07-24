package com.finanzas.app.shared.presentation.filters;

import com.finanzas.app.shared.domain.model.TipoObjeto;

public enum TipoObjetoFilter {
    TODOS,
    SISTEMA,
    USUARIO;

    public TipoObjeto toTipoObjeto() {
        return switch (this) {
            case SISTEMA -> TipoObjeto.SISTEMA;
            case USUARIO -> TipoObjeto.USUARIO;
            case TODOS -> null;
        };
    }
}