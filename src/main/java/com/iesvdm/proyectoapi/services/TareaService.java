package com.iesvdm.proyectoapi.services;

import com.iesvdm.proyectoapi.domain.Tarea;
import com.iesvdm.proyectoapi.repository.TareaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class TareaService {

    private final TareaRepository tareaRepository;

    public TareaService(TareaRepository tareaRepository) {
        this.tareaRepository = tareaRepository;
    }

    public java.util.Optional<Tarea> obtenerTareaPorId(Long id) {
        return tareaRepository.findById(id);
    }

    public Tarea cambiarNombre(Tarea t, Tarea tarea) {
        if (Objects.equals(t.getId(), tarea.getId())) {
            t.setTitulo(tarea.getTitulo());
            return this.tareaRepository.save(t);
        }
        return null;
    }

    public List<Tarea> obtenerTareas() {
        return tareaRepository.findAll();
    }

    public Tarea crearTarea(Tarea tarea) {
        return tareaRepository.save(tarea);
    }

    public boolean eliminarTarea(Long id) {
        if (tareaRepository.existsById(id)) {
            tareaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
