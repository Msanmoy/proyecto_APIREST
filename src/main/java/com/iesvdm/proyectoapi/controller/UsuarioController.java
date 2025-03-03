package com.iesvdm.proyectoapi.controller;

import com.iesvdm.proyectoapi.domain.Usuario;
import com.iesvdm.proyectoapi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioRepository repository;

    @GetMapping
    public Page<Usuario> obtenerTodos(@RequestParam Optional<String> nombre,
                                      @RequestParam Optional<Integer> page,
                                      @RequestParam Optional<String> sortBy) {
        Pageable pageable = PageRequest.of(page.orElse(0), 10, Sort.by(sortBy.orElse("id")));
        return nombre.map(n -> repository.findByNombreContainingIgnoreCase(n, pageable))
                .orElse(repository.findAll(pageable));
    }

    @PostMapping
    public Usuario crear(@RequestBody Usuario entity) { return repository.save(entity); }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerPorId(@PathVariable Long id) {
        return repository.findById(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizar(@PathVariable Long id, @RequestBody Usuario entity) {
        return repository.findById(id).map(e -> {
            e.setNombre(entity.getNombre());
            e.setEmail(entity.getEmail());
            e.setRol(entity.getRol());
            return ResponseEntity.ok(repository.save(e));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
