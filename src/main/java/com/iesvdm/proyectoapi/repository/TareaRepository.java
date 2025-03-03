package com.iesvdm.proyectoapi.repository;

import com.iesvdm.proyectoapi.domain.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TareaRepository extends JpaRepository<Tarea, Long> {}
