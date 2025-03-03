package com.iesvdm.proyectoapi.controller;

import com.iesvdm.proyectoapi.domain.Tarea;
import com.iesvdm.proyectoapi.repository.TareaRepository;
import com.iesvdm.proyectoapi.services.TareaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("tareas")
public class TareaController {
    @Autowired
    private TareaRepository repository;
    @Autowired
    private TareaService tareaService;

    @PutMapping("/{id}/cambiarNombre")
    public Tarea cambiarNombre(@PathVariable("id") long id, @RequestBody Tarea tarea) {
        Tarea t = tareaService.one(id);
        return this.tareaService.cambiarNombre(t, tarea);
    }

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

    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") long id) {
        this.tareaService.eliminarTarea(id);
        log.info("Se ha eliminado la tarea con id: {}", id);
    }
}
