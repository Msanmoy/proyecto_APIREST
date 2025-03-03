package com.iesvdm.proyectoapi.domain;

import lombok.*;
import jakarta.persistence.*;

import java.time.LocalDate;


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
    private LocalDate fechaEntrega;
    @ManyToOne
    private Asignatura asignatura;

    @Override
    public int compareTo(Tarea o) {
        return this.titulo.compareTo(o.getTitulo());
    }



}
