package com.iesvdm.proyectoapi.domain;
import lombok.*;
import jakarta.persistence.*;

import java.time.LocalDate;


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
    private LocalDate fecha;
    @ManyToOne
    private Asignatura asignatura;
}