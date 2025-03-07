package com.iesvdm.proyectoapi.controller;

import com.iesvdm.proyectoapi.domain.Examen;
import com.iesvdm.proyectoapi.repository.ExamenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/examenes")
public class ExamenController {
    @Autowired
    private ExamenRepository repository;

    @GetMapping
    public Page<Examen> obtenerTodos(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @PostMapping
    public Examen crear(@RequestBody Examen entity) { return repository.save(entity); }

    @GetMapping("/{id}")
    public ResponseEntity<Examen> obtenerPorId(@PathVariable Long id) {
        return repository.findById(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Examen> actualizar(@PathVariable Long id, @RequestBody Examen entity) {
        return repository.findById(id).map(e -> {
            e.setTitulo(entity.getTitulo());
            e.setAsignatura(entity.getAsignatura());
            e.setFecha(entity.getFecha());
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