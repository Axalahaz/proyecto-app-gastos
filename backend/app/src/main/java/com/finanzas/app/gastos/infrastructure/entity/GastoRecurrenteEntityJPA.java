package com.finanzas.app.gastos.infrastructure.entity;

import java.time.LocalDateTime;

import com.finanzas.app.gastos.domain.entity.Frecuencia;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(
	    name = "gastos_recurrentes",
	    indexes = {
	        @Index(
	            name = "idx_gasto_rec_categoria",
	            columnList = "categoria_gasto_id"
	        )
	    }
	)
public class GastoRecurrenteEntityJPA {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "usuario_id")
	private Long usuarioId;
	
	@Column(nullable = false)
	private String descripcion; 

	@Column(name = "categoria_gasto_id", nullable = false)
	private Long categoriaGastoId;
	
	@Column()
    private Integer diaVencimiento;

	@Column()
	private Integer mesVencimiento;

    @Enumerated(EnumType.STRING)
    @Column()
    private Frecuencia frecuencia;
    
    @Column(nullable = false)
    private boolean periodicidadActiva;
    
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
    		Long usuarioId,
    		String descripcion,
    		Long categoriaGastoId,
            Integer diaVencimiento,
            Integer mesVencimiento,
            Frecuencia frecuencia,
            boolean periodicidadActiva,
            boolean activo,
    		LocalDateTime fechaCreacion,
    		LocalDateTime fechaCambioActivo
    		) {
    	GastoRecurrenteEntityJPA entity = new GastoRecurrenteEntityJPA();
    	
    	entity.id = id;
    	entity.usuarioId = usuarioId;
    	entity.descripcion = descripcion;
    	entity.categoriaGastoId = categoriaGastoId;
    	entity.diaVencimiento = diaVencimiento;
    	entity.mesVencimiento = mesVencimiento;
    	entity.frecuencia = frecuencia;
    	entity.periodicidadActiva = periodicidadActiva;
    	entity.activo = activo;
    	entity.fechaCreacion = fechaCreacion;
    	entity.fechaCambioActivo = fechaCambioActivo;
    	return entity;
    }
}