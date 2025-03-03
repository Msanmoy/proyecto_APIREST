package com.iesvdm.proyectoapi.services;

import com.iesvdm.proyectoapi.domain.*;
import com.iesvdm.proyectoapi.enums.Rol;
import com.iesvdm.proyectoapi.exception.NotFoundException;
import com.iesvdm.proyectoapi.repository.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.*;

import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Page<Usuario> all(Pageable pageable) {
        return this.usuarioRepository.findAll(pageable);
    }

    public Page<Usuario> allByFilter(String buscar, Pageable pageable) {
        return this.usuarioRepository.findByNombreCompleto(buscar, pageable);
    }

    public Usuario crearUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public boolean existsByEmail(String email) {
        return usuarioRepository.existsByEmail(email);
    }

    public Usuario one(Long id) {
        return this.usuarioRepository.findById(id).orElseThrow(() -> new NotFoundException(id, "usuario"));
    }

    public Usuario one(String email) {
        return this.usuarioRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("Usuario con email: " + email + " no encontrado"));
    }

    public Usuario replace(Long id, Usuario usuario) {
        return this.usuarioRepository.findById(id).map(
                p -> (id.equals(usuario.getId()) && p.getRol().equals(Rol.PROFESOR) ? this.usuarioRepository.save(usuario) : null))
                .orElseThrow(() -> new NotFoundException(id, "usuario"));
    }

    public void cambiarContrasenia(String email, String newPassword) {
        Usuario usuario = one(email);
        usuario.setPassword(newPassword);
        this.usuarioRepository.save(usuario);
    }

    public void delete(Long id) {
        this.usuarioRepository.findById(id).map(e -> {
            this.usuarioRepository.delete(e);
            return e;
        }).orElseThrow(() -> new NotFoundException(id, "usuario"));
    }
}
