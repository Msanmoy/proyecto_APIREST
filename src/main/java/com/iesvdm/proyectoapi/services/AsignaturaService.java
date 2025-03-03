package com.iesvdm.proyectoapi.services;

import com.iesvdm.proyectoapi.domain.Asignatura;
import com.iesvdm.proyectoapi.repository.AsignaturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AsignaturaService {
    @Autowired
    private AsignaturaRepository asignaturaRepository;

    public List<Asignatura> obtenerAsignaturas() {
        return asignaturaRepository.findAll();
    }

    public Asignatura crearAsignatura(Asignatura asignatura) {
        return asignaturaRepository.save(asignatura);
    }

    public java.util.Optional<Asignatura> obtenerAsignaturaPorId(Long id) {
        return asignaturaRepository.findById(id);
    }

    public boolean eliminarAsignatura(Long id) {
        if (asignaturaRepository.existsById(id)) {
            asignaturaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
