package com.iesvdm.proyectoapi.domain;

import lombok.*;
import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Tarea implements Comparable<Tarea>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String descripcion;
    private LocalDateTime fechaEntrega;

    @ManyToOne
    @JoinTable(
            name = "asignatura_tareas",
            joinColumns = @JoinColumn(name = "tareas_id"),
            inverseJoinColumns = @JoinColumn(name = "asignatura_id")
    )
    private Asignatura asignatura;

    @ManyToOne
    @JoinTable(
            name = "calendario_tareas",
            joinColumns = @JoinColumn(name = "tareas_id"),
            inverseJoinColumns = @JoinColumn(name = "calendario_id")
    )
    private Calendario calendario;

    @Override
    public int compareTo(Tarea o) {
        return this.titulo.compareTo(o.getTitulo());
    }



}
