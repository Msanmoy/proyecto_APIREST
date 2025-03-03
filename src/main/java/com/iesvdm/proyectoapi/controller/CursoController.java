package com.iesvdm.proyectoapi.controller;

import com.iesvdm.proyectoapi.domain.Curso;
import com.iesvdm.proyectoapi.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("cursos")
public class CursoController {
    @Autowired
    private CursoRepository repository;

    @GetMapping
    public Page<Curso> obtenerTodos(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @PostMapping
    public Curso crear(@RequestBody Curso entity) { return repository.save(entity); }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> obtenerPorId(@PathVariable Long id) {
        return repository.findById(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Curso> actualizar(@PathVariable Long id, @RequestBody Curso entity) {
        return repository.findById(id).map(e -> {
            e.setNombre(entity.getNombre());
            e.setAsignaturas(entity.getAsignaturas());
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
