package com.iesvdm.proyectoapi.repository;

import com.iesvdm.proyectoapi.domain.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "usuarios", path = "usuarios")
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Page<Usuario> findByNombreContainingIgnoreCase(@Param("nombre") String nombre, Pageable pageable);
}
