package com.iesvdm.proyectoapi.repository;

import com.iesvdm.proyectoapi.domain.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {}