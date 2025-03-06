package com.iesvdm.proyectoapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.iesvdm.proyectoapi.enums.Rol;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    @Column(nullable = false)
    private String nombre;

    private String apellidos;

    @Column(nullable = false, unique = true)
    @Email(regexp = "^[a-z0-9]+@g.educaand.es$") // Solo pueden acceder alumnos con el email del centro educativo.
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Rol rol;

    @ManyToOne
    @JsonIgnore
    private Curso curso;

}
