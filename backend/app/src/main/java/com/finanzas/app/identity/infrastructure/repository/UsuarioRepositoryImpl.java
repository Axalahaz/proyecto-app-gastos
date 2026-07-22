package com.finanzas.app.identity.infrastructure.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.finanzas.app.identity.domain.entity.Usuario;
import com.finanzas.app.identity.domain.repository.UsuarioRepository;
import com.finanzas.app.identity.infrastructure.UsuarioMapper;
import com.finanzas.app.identity.infrastructure.entity.UsuarioEntityJPA;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class UsuarioRepositoryImpl implements UsuarioRepository {

    private final UsuarioRepositoryJPA jpaRepository;
    private final UsuarioMapper usuarioMapper;

    @Override
    public Usuario guardar(Usuario usuario) {
    	UsuarioEntityJPA entity = usuarioMapper.mapToEntity(usuario);
    	UsuarioEntityJPA guardado = jpaRepository.save(entity);
        return usuarioMapper.mapToDomain(guardado);
    }

    @Override
    public Optional<Usuario> buscarPorEmail(String email) {
        return jpaRepository.findByEmail(email)
                .map(usuarioMapper::mapToDomain);
    }
    
    @Override
	public Optional<Usuario> buscarPorId(Long id) {
    	return jpaRepository.findById(id)
                .map(usuarioMapper::mapToDomain);
	}

	@Override
	public void  eliminar(Long id) {
		jpaRepository.deleteById(id);
	}
    
}