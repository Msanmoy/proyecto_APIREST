package com.iesvdm.proyectoapi.services;

import com.iesvdm.proyectoapi.domain.Examen;
import com.iesvdm.proyectoapi.repository.ExamenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamenService {
    @Autowired
    private ExamenRepository examenRepository;

    public List<Examen> obtenerExamenes() {
        return examenRepository.findAll();
    }

    public Examen crearExamen(Examen examen) {
        return examenRepository.save(examen);
    }

    public java.util.Optional<Examen> obtenerExamenPorId(Long id) {
        return examenRepository.findById(id);
    }

    public boolean eliminarExamen(Long id) {
        if (examenRepository.existsById(id)) {
            examenRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
