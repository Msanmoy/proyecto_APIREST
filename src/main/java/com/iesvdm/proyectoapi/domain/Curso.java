package com.iesvdm.proyectoapi.domain;

import lombok.*;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    @ManyToMany(mappedBy = "cursos")
    private Set<Asignatura> asignaturas;
    @OneToMany(mappedBy = "curso", cascade = {
            CascadeType.PERSIST, CascadeType.MERGE
    })
    private Set<Usuario> usuario;

}
