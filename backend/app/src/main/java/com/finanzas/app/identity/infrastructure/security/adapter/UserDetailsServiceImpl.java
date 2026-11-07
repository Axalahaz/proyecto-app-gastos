package com.finanzas.app.identity.infrastructure.security.adapter;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.finanzas.app.identity.domain.Usuario;
import com.finanzas.app.identity.domain.UsuarioRepository;

import lombok.RequiredArgsConstructor;

/**
 * Adaptador de infraestructura que permite a Spring Security
 * recuperar usuarios desde el repositorio del dominio.
 *
 * Implementa {@link UserDetailsService}, proporcionando los mecanismos
 * necesarios para buscar un usuario mediante su correo electrónico
 * durante el proceso de autenticación y mediante su identificador
 * durante la validación de tokens JWT.
 *
 * Una vez recuperado el usuario, lo adapta a {@link UserDetailsAdapter},
 * que implementa el contrato {@link UserDetails} requerido
 * por Spring Security.
 *
 * De esta manera, mantiene desacoplado el modelo de dominio
 * de los mecanismos de autenticación propios del framework.
 *
 * Utiliza {@link UsuarioRepository} para acceder a los usuarios
 * y Lombok para generar el constructor requerido por la inyección
 * de dependencias.
 *
 * @see UsuarioRepository
 * @see UserDetailsAdapter
 * @see UserDetailsService
 */
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

	/**
     * Repositorio del dominio utilizado para recuperar usuarios
     * mediante los criterios de búsqueda necesarios.
     */
	private final UsuarioRepository usuarioRepository;

	/**
     * Busca un usuario mediante su correo electrónico y lo adapta
     * al modelo requerido por Spring Security.
     *
     * Spring Security invoca este método durante el proceso
     * de autenticación con credenciales, utilizando el identificador
     * proporcionado por el usuario durante el inicio de sesión.
     *
     * Si no se encuentra un usuario asociado al correo electrónico,
     * se lanza una excepción de autenticación.
     *
     * @param email correo electrónico utilizado como identificador
     *              durante el inicio de sesión.
     * @return usuario adaptado al contrato {@link UserDetails}.
     * @throws UsernameNotFoundException si no existe un usuario
     *                                   asociado al correo electrónico.
     */
    @Override
    public UserDetails loadUserByUsername(String email) {
    	
    	Usuario usuario = usuarioRepository.buscarPorEmail(email)
    			.orElseThrow(() -> new UsernameNotFoundException("Credenciales inválidas"));
    	
    	return new UserDetailsAdapter(usuario);
    }
    
    /**
     * Busca un usuario mediante su identificador y lo adapta
     * al modelo requerido por Spring Security.
     *
     * Este método es utilizado por el filtro JWT para recuperar
     * al usuario asociado al identificador almacenado en el
     * subject del token.
     *
     * A diferencia de {@link #loadUserByUsername(String)},
     * no forma parte del contrato de {@link UserDetailsService},
     * sino que constituye un mecanismo adicional de búsqueda
     * utilizado por la infraestructura de seguridad de la aplicación.
     *
     * Si no se encuentra un usuario asociado al identificador,
     * se lanza una excepción de autenticación.
     *
     * @param usuarioId identificador del usuario obtenido del token JWT.
     * @return usuario adaptado al contrato {@link UserDetails}.
     * @throws UsernameNotFoundException si no existe un usuario
     *                                   asociado al identificador.
     */
    public UserDetails loadUserById(Long usuarioId) {

        Usuario usuario = usuarioRepository.buscarPorId(usuarioId)
                .orElseThrow(() ->
                    new UsernameNotFoundException("Usuario no encontrado"));

        return new UserDetailsAdapter(usuario);
    }
}