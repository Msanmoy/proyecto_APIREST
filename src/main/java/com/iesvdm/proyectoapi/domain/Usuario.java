package com.iesvdm.proyectoapi.domain;

import com.iesvdm.proyectoapi.enums.Rol;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    @Enumerated(EnumType.STRING)
    private Rol rol;
}
