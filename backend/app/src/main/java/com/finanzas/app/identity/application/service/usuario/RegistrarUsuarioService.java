package com.finanzas.app.identity.application.service.usuario;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finanzas.app.identity.application.exception.EmailYaRegistradoException;
import com.finanzas.app.identity.domain.PasswordEncoder;
import com.finanzas.app.identity.domain.entity.Usuario;
import com.finanzas.app.identity.domain.repository.UsuarioRepository;
import com.finanzas.app.shared.domain.vo.Fecha;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/**
 * Caso de uso que representa el registro de un nuevo usuario en el sistema.
 *
 * Encapsula el flujo completo de creación de un usuario:
 * - Validación de reglas de negocio a nivel de aplicación.
 * - Creación de la entidad de dominio Usuario.
 * - Persistencia a través del repositorio del dominio.
 *
 * Responsabilidades:
 * - Orquestar la creación de usuarios.
 * - Garantizar que no existan emails duplicados.
 *
 * Reglas:
 * - No permite registrar dos usuarios con el mismo email.
 *
 * Dependencias:
 * - Depende de la abstracción UsuarioRepository (dominio).
 *
 * Nota:
 * - Esta clase pertenece a la capa de aplicación y no contiene lógica de dominio.
 */
@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class RegistrarUsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public Usuario ejecutar(String email, String password) {

        usuarioRepository.buscarPorEmail(email)
            .ifPresent(u -> {
                throw new EmailYaRegistradoException();
            });

        Fecha fechaRegistro = new Fecha(LocalDateTime.now());
        String passwordEncriptada = passwordEncoder.encode(password);
        
        Usuario usuario = Usuario.crear(email, passwordEncriptada, fechaRegistro);
        
        Usuario guardado = usuarioRepository.guardar(usuario);
        
        log.info("Usuario registrado correctamente");
        
        return guardado;
    }
}