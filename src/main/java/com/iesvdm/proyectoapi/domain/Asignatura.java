package com.iesvdm.proyectoapi.domain;

import lombok.*;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Asignatura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    @ManyToMany
    @JoinTable(name = "curso_asignaturas",
            joinColumns = @JoinColumn(name = "asignaturas_id"),
            inverseJoinColumns = @JoinColumn(name = "cursos_id")
    )
    private Set<Curso> cursos;

    @ManyToMany
    @JoinTable(
            name = "asignatura_tareas",
            joinColumns = @JoinColumn(name = "asignatura_id"),
            inverseJoinColumns = @JoinColumn(name = "tareas_id")
    )
    private Set<Tarea> tareas;

    @ManyToMany
    @JoinTable(
            name = "asignatura_examenes",
            joinColumns = @JoinColumn(name = "asignatura_id"),
            inverseJoinColumns = @JoinColumn(name = "examenes_id")
    )
    private Set<Examen> examenes;
}
