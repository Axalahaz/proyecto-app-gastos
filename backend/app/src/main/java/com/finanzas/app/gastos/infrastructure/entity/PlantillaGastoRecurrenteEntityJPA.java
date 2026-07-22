package com.finanzas.app.gastos.infrastructure.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "plantilla_gastos_recurrentes")
public class PlantillaGastoRecurrenteEntityJPA {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(nullable = false)
	private String descripcion; 
	
	@Column(nullable = false)
	private boolean activo;
	
	@Column(nullable = false)
    private LocalDateTime fechaCreacion;
	
    // ----------------------------------
    // OF 
    
    public static PlantillaGastoRecurrenteEntityJPA of(
    		Long id,
    		String descripcion,
    		boolean activo,
    		LocalDateTime fechaCreacion
    		) {
    	PlantillaGastoRecurrenteEntityJPA entity = new PlantillaGastoRecurrenteEntityJPA();
    	
    	entity.id = id;
    	entity.descripcion = descripcion;
    	entity.activo = activo;
    	entity.fechaCreacion = fechaCreacion;
    	return entity;
    }
}