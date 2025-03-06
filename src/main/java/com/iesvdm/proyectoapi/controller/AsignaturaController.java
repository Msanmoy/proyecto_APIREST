package com.iesvdm.proyectoapi.controller;

import com.iesvdm.proyectoapi.domain.Asignatura;
import com.iesvdm.proyectoapi.repository.AsignaturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v1/api/asignaturas")
public class AsignaturaController {
    @Autowired
    private AsignaturaRepository repository;

    @GetMapping
    public Page<Asignatura> obtenerTodos(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @PostMapping
    public Asignatura crear(@RequestBody Asignatura entity) { return repository.save(entity); }

    @GetMapping("/{id}")
    public ResponseEntity<Asignatura> obtenerPorId(@PathVariable Long id) {
        return repository.findById(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Asignatura> actualizar(@PathVariable Long id, @RequestBody Asignatura entity) {
        return repository.findById(id).map(e -> {
            e.setNombre(entity.getNombre());
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
