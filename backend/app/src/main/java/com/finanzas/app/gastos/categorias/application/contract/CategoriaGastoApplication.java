package com.finanzas.app.gastos.categorias.application.contract;

/**
 * Define el contrato público del componente categorías para
 * establecer la comunicación con otros componentes de la aplicación.
 */
public interface CategoriaGastoApplication {

	// comunicacion para obtener datos.
    CategoriaGastoData obtenerDatos(
    		Long categoriaGastoId, 
    		Long usuarioId
    );
    
    // Comunicacion para comprobar que no haya duplicados
    boolean existePorNombre(
    		String nombre, 
    		Long usuarioId
    );
    
    void existePorId(
    		Long categoriaGastoId,  
    		Long usuarioId
    );
}