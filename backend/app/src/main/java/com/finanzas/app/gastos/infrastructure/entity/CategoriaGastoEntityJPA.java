package com.finanzas.app.gastos.infrastructure.entity;

import java.time.LocalDateTime;

import com.finanzas.app.shared.domain.model.TipoObjeto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "categorias_gastos")
public class CategoriaGastoEntityJPA {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
	@Column(nullable = false)
    private String nombre;
	
	@Column(nullable = false)
	private TipoObjeto tipo;
	
	@Column(nullable = false)
	private LocalDateTime fechaCreacion;
    
    // ----------------------------------
    // OF

    public static CategoriaGastoEntityJPA of(
    		Long id,
    		String nombre,
    		TipoObjeto tipo,
    		LocalDateTime fechaCreacion
    		) {
    	CategoriaGastoEntityJPA entity = new CategoriaGastoEntityJPA();
    	
    	entity.id = id;
    	entity.nombre = nombre;
    	entity.tipo = tipo;
    	entity.fechaCreacion = fechaCreacion;
    	return entity;
    }
    
}