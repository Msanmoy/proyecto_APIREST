package com.iesvdm.proyectoapi.controller;

import com.iesvdm.proyectoapi.domain.Calendario;
import com.iesvdm.proyectoapi.repository.CalendarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("calendarios")
public class CalendarioController {
    @Autowired
    private CalendarioRepository repository;

    @GetMapping
    public Page<Calendario> obtenerTodos(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @PostMapping
    public Calendario crear(@RequestBody Calendario entity) { return repository.save(entity); }

    @GetMapping("/{id}")
    public ResponseEntity<Calendario> obtenerPorId(@PathVariable Long id) {
        return repository.findById(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Calendario> actualizar(@PathVariable Long id, @RequestBody Calendario entity) {
        return repository.findById(id).map(e -> {
            e.setExamenes(entity.getExamenes());
            e.setTareas(entity.getTareas());
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