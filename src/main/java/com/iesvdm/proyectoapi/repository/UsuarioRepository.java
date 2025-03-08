package com.iesvdm.proyectoapi.repository;

import com.iesvdm.proyectoapi.domain.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

// Personaliza la exposición de un repositorio JPA como una API REST automáticamente
@RepositoryRestResource(collectionResourceRel = "usuarios", path = "usuarios")
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    @Query("SELECT u FROM Usuario u WHERE CONCAT(u.nombre, ' ', u.apellidos) LIKE %?1%")
    Page<Usuario> findByNombreCompleto(String nombreCompleto, Pageable pageable);
    Optional<Usuario> findByEmail(String email);
    Boolean existsByEmail(String email);

}
