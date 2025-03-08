package com.iesvdm.proyectoapi;

import com.iesvdm.proyectoapi.controller.UsuarioController;
import com.iesvdm.proyectoapi.domain.Usuario;
import com.iesvdm.proyectoapi.enums.Rol;
import com.iesvdm.proyectoapi.repository.UsuarioRepository;
import com.iesvdm.proyectoapi.services.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
class UsuarioTests {
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioController usuarioController;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @BeforeEach
    void setUp() {
        usuarioRepository.deleteAll();

        Usuario prueba1 = new Usuario();
        prueba1.setNombre("prueba1");
        prueba1.setApellidos("prueba1");
        prueba1.setEmail("prueba1@g.educaand.es");
        prueba1.setPassword("123456");
        prueba1.setRol(Rol.PROFESOR);

        Usuario prueba2 = new Usuario();
        prueba2.setNombre("prueba2");
        prueba2.setApellidos("prueba2");
        prueba2.setEmail("prueba2@g.educaand.es");
        prueba2.setPassword("123456");
        prueba2.setRol(Rol.ALUMNO);

        Usuario prueba3 = new Usuario();
        prueba3.setNombre("prueba3");
        prueba3.setApellidos("prueba3");
        prueba3.setEmail("prueba3@g.educaand.es");
        prueba3.setPassword("123456");
        prueba3.setRol(Rol.ALUMNO);

        usuarioRepository.save(prueba1);
        usuarioRepository.save(prueba2);
        usuarioRepository.save(prueba3);
    }

    @Test
    void testCrearUsuario() {
        Usuario usuario = new Usuario();
        usuario.setNombre("Manuel");
        usuario.setApellidos("Sanchez Moya");
        usuario.setEmail("manuel@g.educaand.es");
        usuario.setPassword("123456");
        usuario.setRol(Rol.ALUMNO);

        usuarioService.crearUsuario(usuario);
        Usuario resultado = usuarioController.one(usuario.getId());
        assertNotNull(resultado);
        assertEquals("Manuel", resultado.getNombre());
    }


    @Test
    void testAllUsuarios() {
        Pageable pageable = PageRequest.of(0, 2);
        Page<Usuario> pagina = usuarioController.all(pageable);

        assertNotNull(pagina);
        assertEquals(2, pagina.getSize());
        assertEquals(3, pagina.getTotalElements());
    }

    @Test
    void testAllUsuariosConFiltro() {
        Pageable pageable = PageRequest.of(0,10);
        Page<Usuario> pagina = usuarioController.all("Prueba1", pageable);

        assertNotNull(pagina);
        assertEquals(1, pagina.getTotalElements());
        assertEquals("prueba1", pagina.getContent().getFirst().getNombre());
    }

    @Test
    void testReplaceUsuario() {
        Usuario usuarioNuevo = new Usuario();

        usuarioNuevo.setNombre("Actualizado"); usuarioNuevo.setApellidos("Actualizado"); usuarioNuevo.setEmail("actualizado@g.educaand.es"); usuarioNuevo.setPassword("123456"); usuarioNuevo.setRol(Rol.PROFESOR);

        Pageable pageable = PageRequest.of(0,10);

        Usuario usuario = usuarioController.all(pageable).getContent().getFirst();

        Usuario usuarioResultado = usuarioController.replace(usuario.getId(), usuarioNuevo);

        assertNotNull(usuarioResultado);
        assertEquals("Actualizado", usuarioResultado.getNombre());
        assertEquals("Actualizado", usuarioResultado.getApellidos());
        assertEquals("actualizado@g.educaand.es", usuarioResultado.getEmail());

        Usuario usuarioDB = usuarioRepository.findById(usuario.getId()).orElse(null);
        assertNotNull(usuarioDB);
        assertEquals("Actualizado", usuarioDB.getApellidos());
    }


    @Test
    void testCambiarContrasenia() {
        String contraseniaNueva = "654321";
        Usuario usuario1 = usuarioController.one(usuarioService.one("prueba2@g.educaand.es").getId());

        String contraseniaAntigua = usuario1.getPassword();

        usuarioController.cambiarContrasenia(usuario1, contraseniaNueva);

        usuario1 = usuarioController.one(usuarioService.one("prueba2@g.educaand.es").getId());

        assertNotNull(usuario1);
        assertEquals(contraseniaNueva, usuario1.getPassword());
        assertNotEquals(contraseniaAntigua, usuario1.getPassword());
    }

    @Test
    void testBuscarUsuarioPorId() {
        Usuario usuario = new Usuario();
        usuario.setNombre("prueba1");
        usuario.setApellidos("Prueba");
        usuario.setEmail("prueba123@g.educaand.es");
        usuario.setPassword("123456");
        usuario.setRol(Rol.ALUMNO);

        usuarioService.crearUsuario(usuario);

        Optional<Usuario> prueba = usuarioRepository.findByEmail("prueba123@g.educaand.es");
        assertTrue(prueba.isPresent());
        assertNotNull(prueba);
        assertEquals("prueba1", prueba.get().getNombre());
    }

    @Test
    void testBuscarUsuarioPorEmail() {
        Usuario usuario = new Usuario();
        usuario.setNombre("prueba2");
        usuario.setApellidos("Prueba");
        usuario.setEmail("prueba1234@g.educaand.es");
        usuario.setPassword("123456");
        usuario.setRol(Rol.ALUMNO);

        usuarioService.crearUsuario(usuario);

        assertTrue(usuarioService.existsByEmail("prueba1234@g.educaand.es"));
    }

    @Test
    void testBorrarUsuario() {
        Pageable pageable1 = PageRequest.of(0, 3);
        Page<Usuario> pagina1 = usuarioController.all(pageable1);
        usuarioController.delete(usuarioService.one("prueba3@g.educaand.es").getId());
        Page<Usuario> pagina2 = usuarioController.all(pageable1);

        assertNotNull(pagina1);
        assertNotNull(pagina2);
        assertNotEquals(pagina1, pagina2);

    }

    @Test
    void testBorrarTodosUsuarios() {
        Pageable pageable1 = PageRequest.of(0, 3);
        Page<Usuario> pagina1 = usuarioController.all(pageable1);

        usuarioController.deleteAll();

        pagina1 = usuarioController.all(pageable1);

        assertEquals(0, pagina1.getTotalElements());

    }



}
