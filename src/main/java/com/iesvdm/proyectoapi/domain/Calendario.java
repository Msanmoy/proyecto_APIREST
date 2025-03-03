package com.iesvdm.proyectoapi.domain;

import lombok.*;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Calendario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany
    private Set<Tarea> tareas;
    @OneToMany
    private Set<Examen> examenes;
}