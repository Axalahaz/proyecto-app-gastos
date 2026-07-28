package com.finanzas.app.gastos.infrastructure.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

import com.finanzas.app.shared.domain.model.TipoObjeto;

import jakarta.persistence.Column;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "plantilla_gastos")
public class PlantillaGastoEntityJPA {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "categoria_gasto_id")
	private Long categoriaGastoId;
	
	@Column(nullable = false)
	private String descripcion; 
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private TipoObjeto tipo;
	
	@Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;
	
    // ----------------------------------
    // OF 
    
    public static PlantillaGastoEntityJPA of(
    		Long id,
    		Long categoriaGastoId,
    		String descripcion,
    		TipoObjeto tipo,
    		LocalDateTime fechaCreacion
    		) {
    	PlantillaGastoEntityJPA entity = new PlantillaGastoEntityJPA();
    	
    	entity.id = id;
    	entity.categoriaGastoId = categoriaGastoId;
    	entity.descripcion = descripcion;
    	entity.tipo = tipo;
    	entity.fechaCreacion = fechaCreacion;
    	return entity;
    }
}