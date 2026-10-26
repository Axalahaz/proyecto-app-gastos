package com.finanzas.app.gastos.categorias.infrastructure;

import java.time.LocalDateTime;

import com.finanzas.app.shared.domain.enums.TipoObjeto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(
		name = "categorias_gastos",
		indexes = {
		        @Index(
		            name = "idx_categoria_tipo",
		            columnList = "tipo_objeto"
		        ),
		}
	)
public class CategoriaGastoEntityJPA {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "usuario_id", nullable = false)
	private Long usuarioId;
    
	@Column(nullable = false)
    private String nombre;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_objeto", nullable = false)
	private TipoObjeto tipo;
	
	@Column(nullable = false)
	private LocalDateTime fechaCreacion;
    
    // ----------------------------------
    // OF

    public static CategoriaGastoEntityJPA of(
    		Long id,
    		Long usuarioId,
    		String nombre,
    		TipoObjeto tipo,
    		LocalDateTime fechaCreacion
    		) {
    	CategoriaGastoEntityJPA entity = new CategoriaGastoEntityJPA();
    	
    	entity.id = id;
    	entity.usuarioId = usuarioId;
    	entity.nombre = nombre;
    	entity.tipo = tipo;
    	entity.fechaCreacion = fechaCreacion;
    	return entity;
    }
    
}