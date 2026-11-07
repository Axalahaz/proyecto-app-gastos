package com.finanzas.app.identity.infrastructure.security.adapter;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.finanzas.app.identity.domain.Usuario;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Adaptador de infraestructura que permite integrar la entidad de dominio
 * {@link Usuario} con el modelo de usuario requerido por Spring Security.
 *
 * Implementa {@link UserDetails}, proporcionando a Spring Security
 * la información necesaria para identificar al usuario, validar sus
 * credenciales, consultar su estado y obtener sus autoridades.
 *
 * Evita que el dominio dependa directamente de las interfaces
 * y características propias de Spring Security, manteniendo
 * separadas las responsabilidades de cada capa.
 *
 * También expone información del dominio relacionada con los cambios
 * de contraseña y estado del usuario, utilizada para determinar
 * si un token JWT continúa siendo válido.
 *
 * Utiliza Lombok para generar el constructor y los métodos getter
 * correspondientes a sus atributos.
 *
 * @see Usuario
 * @see UserDetails
 * @see UserDetailsServiceImpl
 */
@Getter
@RequiredArgsConstructor
public class UserDetailsAdapter implements UserDetails {

	/**
     * Identificador de versión utilizado durante los procesos
     * de serialización y deserialización de objetos.
     *
     * Permite identificar la versión de esta clase para comprobar
     * su compatibilidad cuando se recuperan objetos serializados.
     */
    private static final long serialVersionUID = -2655173230397146547L;
    
    /**
     * Usuario del dominio cuya información será adaptada
     * al contrato requerido por Spring Security.
     */
	private final Usuario usuario;
    
	/**
     * Obtiene la fecha y hora de la última modificación de la contraseña.
     *
     * Recupera el valor del objeto de dominio que representa
     * dicha fecha, contemplando la posibilidad de que no exista.
     *
     * Esta información permite comprobar si el token JWT fue emitido
     * antes de un cambio de contraseña y, por lo tanto,
     * debe considerarse inválido.
     *
     * @return fecha y hora del último cambio de contraseña,
     *         o {@code null} si todavía no existe un registro.
     */
    public LocalDateTime getFechaCambioPassword() {
        return usuario.getFechaCambioPassword() != null
                ? usuario.getFechaCambioPassword().getValue()
                        : null;
    }
    
    /**
     * Obtiene la fecha y hora de la última modificación del estado
     * del usuario.
     *
     * Recupera el valor del objeto de dominio que representa
     * dicha fecha, contemplando la posibilidad de que no exista.
     *
     * Esta información permite invalidar los tokens JWT emitidos
     * antes de un cambio en el estado del usuario.
     *
     * @return fecha y hora del último cambio de estado,
     *         o {@code null} si todavía no existe un registro.
     */
    public LocalDateTime getFechaCambioEstado() {
        return usuario.getFechaCambioEstado() != null
                ? usuario.getFechaCambioEstado().getValue()
                        : null;
    }

    /**
     * Obtiene el identificador utilizado por Spring Security
     * para reconocer al usuario durante el proceso de autenticación.
     *
     * En esta implementación se utiliza el correo electrónico
     * como identificador de acceso.
     *
     * @return correo electrónico del usuario.
     */
    @Override
    public String getUsername() {
        return usuario.getEmail();
    }
    
    /**
     * Determina si el usuario se encuentra habilitado para autenticarse.
     *
     * Delega la comprobación en el modelo de dominio, evitando
     * duplicar la lógica que determina si un usuario está activo.
     *
     * @return {@code true} si el usuario está activo;
     *         {@code false} en caso contrario.
     */
    @Override
    public boolean isEnabled() {
        return usuario.isActivo();
    }

    /**
     * Obtiene el hash de la contraseña almacenada para el usuario.
     *
     * Spring Security utiliza este valor durante la validación
     * de las credenciales, comparándolo con la contraseña
     * proporcionada durante el inicio de sesión.
     *
     * @return hash de la contraseña del usuario.
     */
    @Override
    public String getPassword() {
    	return usuario.getPasswordHash();
    }

    /**
     * Obtiene las autoridades o permisos asociados al usuario.
     *
     * Actualmente la aplicación no implementa una gestión
     * de roles o permisos, por lo que devuelve una colección vacía.
     *
     * Este método permite incorporar posteriormente mecanismos
     * de autorización basados en roles o autoridades sin modificar
     * el contrato de integración con Spring Security.
     *
     * @return colección de autoridades del usuario.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(); 
    }
}