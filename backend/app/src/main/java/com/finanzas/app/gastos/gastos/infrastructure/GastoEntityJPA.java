package com.finanzas.app.gastos.gastos.infrastructure;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.finanzas.app.shared.domain.enums.EstadoMovimiento;

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
	
	@Column(name = "usuario_id", nullable = false)
	private Long usuarioId;
	
	@Column(nullable = false, precision = 12, scale = 2)
	private BigDecimal importe;
	
	@Column(nullable = false)
	private String descripcion; 
	
    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_modificacion")
    private LocalDateTime fechaModificacion;
    
    @Column(name = "fecha_pago_recurrente")
    private LocalDateTime fechaPagoRecurrente;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "estado_movimiento", nullable = false)
    private EstadoMovimiento estado;
    
    @Column(name = "categoria_gasto_id", nullable = false)
	private Long categoriaGastoId;

	@Column(name = "configuracion_gasto_recurrente_id")
	private Long confGastoRecurrenteId;

    // ----------------------------------
    // OF 
    
    public static GastoEntityJPA of(
    		Long id,
    		Long usuarioId,
    		BigDecimal importe,
    		String descripcion,
    		LocalDateTime fechaCreacion,
    		LocalDateTime fechaModificacion,
    		LocalDateTime fechaPagoRecurrente,
    		EstadoMovimiento estado,
    		Long categoriaGastoId,
    		Long confGastoRecurrenteId
    		) {
    	GastoEntityJPA entity = new GastoEntityJPA();
    	
    	entity.id = id;
    	entity.usuarioId = usuarioId;
    	entity.importe = importe;
    	entity.descripcion = descripcion;
    	entity.fechaCreacion = fechaCreacion;
    	entity.fechaModificacion = fechaModificacion;
    	entity.fechaPagoRecurrente = fechaPagoRecurrente;
    	entity.estado = estado;
    	entity.categoriaGastoId = categoriaGastoId;
    	entity.confGastoRecurrenteId = confGastoRecurrenteId;
    	
    	return entity;
    }
}