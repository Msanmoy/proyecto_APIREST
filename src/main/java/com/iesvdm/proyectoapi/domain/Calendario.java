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
    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinTable(
            name = "calendario_tareas",
            joinColumns = @JoinColumn(name = "calendario_id"),
            inverseJoinColumns = @JoinColumn(name = "tareas_id")
    )
    private Set<Tarea> tareas;

    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinTable(
            name = "calendario_examenes",
            joinColumns = @JoinColumn(name = "calendario_id"),
            inverseJoinColumns = @JoinColumn(name = "examenes_id")
    )
    private Set<Examen> examenes;
}