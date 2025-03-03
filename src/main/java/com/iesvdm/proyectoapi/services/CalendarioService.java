package com.iesvdm.proyectoapi.services;

import com.iesvdm.proyectoapi.domain.Calendario;
import com.iesvdm.proyectoapi.repository.CalendarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalendarioService {
    @Autowired
    private CalendarioRepository calendarioRepository;

    public List<Calendario> obtenerCalendarios() {
        return calendarioRepository.findAll();
    }

    public Calendario crearCalendario(Calendario calendario) {
        return calendarioRepository.save(calendario);
    }

    public java.util.Optional<Calendario> obtenerCalendarioPorId(Long id) {
        return calendarioRepository.findById(id);
    }

    public boolean eliminarCalendario(Long id) {
        if (calendarioRepository.existsById(id)) {
            calendarioRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
