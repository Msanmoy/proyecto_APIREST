package com.iesvdm.proyectoapi.domain;
import lombok.*;
import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Examen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private LocalDateTime fecha;

    @ManyToOne
    @JoinTable(
            name = "asignatura_examenes",
            joinColumns = @JoinColumn(name = "examenes_id"),
            inverseJoinColumns = @JoinColumn(name = "asignatura_id")
    )
    private Asignatura asignatura;

    @ManyToOne
    @JoinTable(
            name = "calendario_examenes",
            joinColumns = @JoinColumn(name = "examenes_id"),
            inverseJoinColumns = @JoinColumn(name = "calendario_id")
    )
    private Calendario calendario;
}