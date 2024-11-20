package com.example.TeamUp.Services;

import com.example.TeamUp.Entities.UsuarioEntity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.TeamUp.Repositories.UsuarioRepository;


@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioEntity getUsuarioByEmail(String email) {

        // Utilizamos el método del repositorio para buscar por lemail
        UsuarioEntity usuario = usuarioRepository.findByEmail(email);
        if (usuario == null) {
            throw new RuntimeException("Usuario no encontrado con el email: " + email);
        }
        return usuario;
    }


    //get todos los usuarios
    public List<UsuarioEntity> getUsuarios() {
        return usuarioRepository.findAll();
    }

    //get usuario por id
    public UsuarioEntity getUsuario(Long id) {
        return usuarioRepository.findById(id).orElseThrow(() -> new IllegalStateException("Usuario con id " + id + " no existe"));
    }

    //crear usuario
    public UsuarioEntity addUsuario(UsuarioEntity usuario) {
        return usuarioRepository.save(usuario);
    }

    //actualizar usuario
    public UsuarioEntity updateUsuario(Long id, UsuarioEntity usuario) {
        UsuarioEntity usuarioEntity = usuarioRepository.findById(id).orElseThrow(() -> new IllegalStateException("Usuario con id " + id + " no existe"));
        usuarioEntity.setNombre(usuario.getNombre());
        usuarioEntity.setCarrera(usuario.getCarrera());
        usuarioEntity.setLogin(usuario.getLogin());
        usuarioEntity.setPassword(usuario.getPassword());
        usuarioEntity.setReputacion(usuario.getReputacion());
        usuarioEntity.setCalificacion(usuario.getCalificacion());
        return usuarioRepository.save(usuarioEntity);
    }


    //borrar usuario
    public void deleteUsuario(Long id) {
        boolean exists = usuarioRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Usuario con id " + id + " no existe");
        }
        usuarioRepository.deleteById(id);
    }
    
}
