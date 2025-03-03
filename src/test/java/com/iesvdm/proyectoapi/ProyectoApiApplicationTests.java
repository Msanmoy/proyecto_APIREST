package com.iesvdm.proyectoapi;

import com.iesvdm.proyectoapi.domain.Usuario;
import com.iesvdm.proyectoapi.enums.Rol;
import com.iesvdm.proyectoapi.repository.UsuarioRepository;
import com.iesvdm.proyectoapi.services.UsuarioService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProyectoApiApplicationTests {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private UsuarioService usuarioService;

    @Test
    void testCrearUsuario() {
        Usuario usuario = new Usuario();
        usuario.setEmail("manuel@gmail.com");
        usuario.setNombre("Manuel");
        usuario.setRol(Rol.ALUMNO);

        usuarioRepository.save(usuario);
        Optional<Usuario> resultado = usuarioService.obtenerUsuarioPorId(usuario.getId());
        assertNotNull(resultado);
        assertEquals("Manuel", resultado.get().getNombre());
    }


    @Test
    void testGuardarYBuscarUsuario() {
        Usuario prueba = new Usuario();
        prueba.setNombre("Test");
        prueba.setRol(Rol.PROFESOR);
        prueba.setEmail("test@test.test");
        Usuario guardado = usuarioService.crearUsuario(prueba);

        Optional<Usuario> encontrado = usuarioRepository.findById(guardado.getId());

        assertTrue(encontrado.isPresent());
        assertEquals("Test", encontrado.get().getNombre());
    }

}
