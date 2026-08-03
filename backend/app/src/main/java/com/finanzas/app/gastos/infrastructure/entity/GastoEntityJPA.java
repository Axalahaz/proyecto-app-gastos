package com.finanzas.app.gastos.infrastructure.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.finanzas.app.shared.domain.model.EstadoMovimiento;

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
	    name = "gastos",
	    indexes = {
	        @Index(
	            name = "idx_gasto_categoria",
	            columnList = "categoria_gasto_id"
	        ),
	        @Index(
	                name = "idx_gasto_fecha",
	                columnList = "fecha_creacion"
	            )
	    }
	)
public class GastoEntityJPA {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "categoria_gasto_id", nullable = false)
	private Long categoriaGastoId;
	
	@Column(name = "plantilla_gasto_id")
	private Long plantillaId; 

	@Column(name = "gasto_recurrente_id")
	private Long gastoRecurrenteId;
	
	@Column()
	private String descripcion; // puede estar vacio

	@Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal monto;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoMovimiento estado;
    
    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_vencimiento")
    private LocalDateTime fechaVencimiento;

    @Column(name = "fecha_cambio_estado")
    private LocalDateTime fechaCambioEstado;

    // ----------------------------------
    // OF 
    
    public static GastoEntityJPA of(
    		Long id,
    		Long categoriaGastoId,
    		Long plantillaId,
    		Long gastoRecurrenteId,
    		BigDecimal monto,
    		String descripcion,
    		EstadoMovimiento estado,
    		LocalDateTime fechaCreacion,
    		LocalDateTime fechaVencimiento,
    		LocalDateTime fechaCambioEstado
    		) {
    	GastoEntityJPA entity = new GastoEntityJPA();
    	
    	entity.id = id;
    	entity.categoriaGastoId = categoriaGastoId;
    	entity.plantillaId = plantillaId;
    	entity.gastoRecurrenteId = gastoRecurrenteId;
    	entity.monto = monto;
    	entity.descripcion = descripcion;
    	entity.estado = estado;
    	entity.fechaCreacion = fechaCreacion;
    	entity.fechaVencimiento = fechaVencimiento;
    	entity.fechaCambioEstado = fechaCambioEstado;
    	return entity;
    }
}