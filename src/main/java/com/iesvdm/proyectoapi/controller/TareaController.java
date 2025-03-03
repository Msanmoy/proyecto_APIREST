package com.iesvdm.proyectoapi.controller;

import com.iesvdm.proyectoapi.domain.Tarea;
import com.iesvdm.proyectoapi.repository.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("tareas")
public class TareaController {
    @Autowired
    private TareaRepository repository;

    @GetMapping
    public Page<Tarea> obtenerTodos(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @PostMapping
    public Tarea crear(@RequestBody Tarea entity) { return repository.save(entity); }

    @GetMapping("/{id}")
    public ResponseEntity<Tarea> obtenerPorId(@PathVariable Long id) {
        return repository.findById(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarea> actualizar(@PathVariable Long id, @RequestBody Tarea entity) {
        return repository.findById(id).map(e -> {
            e.setTitulo(entity.getTitulo());
            e.setDescripcion(entity.getDescripcion());
            e.setAsignatura(entity.getAsignatura());
            e.setFechaEntrega(entity.getFechaEntrega());
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
