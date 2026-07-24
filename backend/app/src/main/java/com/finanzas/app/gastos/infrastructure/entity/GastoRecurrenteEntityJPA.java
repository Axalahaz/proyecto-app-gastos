package com.finanzas.app.gastos.infrastructure.entity;

import java.time.LocalDateTime;

import com.finanzas.app.shared.domain.model.Frecuencia;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "gastos_recurrentes")
public class GastoRecurrenteEntityJPA {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column()
	private Long plantillaId; 

	@Column(nullable = false)
	private String descripcion; 

	@Column()
    private Integer diaVencimiento;

	@Column()
	private Integer mesVencimiento;

    @Enumerated(EnumType.STRING)
    @Column()
    private Frecuencia frecuencia;
    
    @Column(nullable = false)
    private boolean activo;
    
    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_cambio_activo")
    private LocalDateTime fechaCambioActivo;

    // ----------------------------------
    // OF 
    
    public static GastoRecurrenteEntityJPA of(
    		Long id,
    		Long plantillaId,
    		String descripcion,
            Integer diaVencimiento,
            Integer mesVencimiento,
            Frecuencia frecuencia,
            boolean activo,
    		LocalDateTime fechaCreacion,
    		LocalDateTime fechaCambioActivo
    		) {
    	GastoRecurrenteEntityJPA entity = new GastoRecurrenteEntityJPA();
    	
    	entity.id = id;
    	entity.plantillaId = plantillaId;
    	entity.descripcion = descripcion;
    	entity.diaVencimiento = diaVencimiento;
    	entity.mesVencimiento = mesVencimiento;
    	entity.frecuencia = frecuencia;
    	entity.activo = activo;
    	entity.fechaCreacion = fechaCreacion;
    	entity.fechaCambioActivo = fechaCambioActivo;
    	return entity;
    }
}