package com.iesvdm.proyectoapi.services;

import com.iesvdm.proyectoapi.domain.*;
import com.iesvdm.proyectoapi.exception.NotFoundException;
import com.iesvdm.proyectoapi.repository.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.*;
import java.util.Optional;

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
        return this.usuarioRepository.findByEmail(email).orElseThrow(() ->
                new NotFoundException("Usuario con email: " + email + " no encontrado"));
    }

    public Usuario replace(Long id, Usuario usuario) {
        return this.usuarioRepository.findById(id).map(usuario1 -> {
            usuario1.setNombre(usuario.getNombre());
            usuario1.setApellidos(usuario.getApellidos());
            usuario1.setEmail(usuario.getEmail());
            usuario1.setPassword(usuario.getPassword());
            usuario1.setRol(usuario.getRol());
            return usuarioRepository.save(usuario1);
        })
                .orElseThrow(() -> new NotFoundException(id, "usuario"));
    }

    public void cambiarContrasenia(String email, String newPassword) {
        Usuario usuario = one(email);
        usuario.setPassword(newPassword);
        usuarioRepository.save(usuario);
    }

    public void delete(Long id) {
        this.usuarioRepository.findById(id).map(e -> {
            this.usuarioRepository.delete(e);
            return e;
        }).orElseThrow(() -> new NotFoundException(id, "usuario"));
    }

    public void deleteAll() {
        this.usuarioRepository.deleteAll();
        System.out.println("Todos los usuarios han sido eliminados de la base de datos");
    }

    public String getNombreCurso(Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isPresent()) {
            if (usuario.get().getCurso() != null) {
                return usuario.get().getCurso().getNombre();
            } else {
                return "El usuario no tiene un curso asignado";
            }
        } else {
            return "usuario no encontrado";
        }

    }
}
