package com.finanzas.app.gastos.recurrentes.infrastructure;

import java.time.LocalDateTime;

import com.finanzas.app.gastos.recurrentes.domain.EstadoConf;
import com.finanzas.app.shared.domain.enums.Frecuencia;

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
@Table(name = "configuracion_gastos_recurrentes")
public class ConfGastoRecurrenteEntityJPA {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "usuario_id", nullable = false)
	private Long usuarioId;
	
	@Column(nullable = false)
	private String descripcion; 

	@Column(name = "categoria_gasto_id", nullable = false)
	private Long categoriaGastoId;

	@Enumerated(EnumType.STRING)
	@Column(name = "frecuencia_recurrencia", nullable = false)
	private Frecuencia frecuencia;

	@Column(name = "dia_vencimiento", nullable = false)
    private Integer diaVencimiento;

	@Column(name = "mes_vencimiento")
	private Integer mesVencimiento;
    
	@Enumerated(EnumType.STRING)
	@Column(name = "estado_configuracion", nullable = false)
    private EstadoConf estado;
    
    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_modificacion")
    private LocalDateTime fechaModificacion;

    // ----------------------------------
    // OF 
    
    public static ConfGastoRecurrenteEntityJPA of(
    		Long id,
    		Long usuarioId,
    		String descripcion,
    		Long categoriaGastoId,
    		Frecuencia frecuencia,
            Integer diaVencimiento,
            Integer mesVencimiento,
            EstadoConf estado,
    		LocalDateTime fechaCreacion,
    		LocalDateTime fechaModificacion
    		) {
    	ConfGastoRecurrenteEntityJPA entity = new ConfGastoRecurrenteEntityJPA();
    	
    	entity.id = id;
    	entity.usuarioId = usuarioId;
    	entity.descripcion = descripcion;
    	entity.categoriaGastoId = categoriaGastoId;
    	entity.frecuencia = frecuencia;
    	entity.diaVencimiento = diaVencimiento;
    	entity.mesVencimiento = mesVencimiento;
    	entity.estado = estado;
    	entity.fechaCreacion = fechaCreacion;
    	entity.fechaModificacion = fechaModificacion;
    	return entity;
    }
}