package com.iesvdm.proyectoapi.controller;

import com.iesvdm.proyectoapi.domain.Usuario;
import com.iesvdm.proyectoapi.repository.UsuarioRepository;
import com.iesvdm.proyectoapi.services.UsuarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/v1/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {this.usuarioService = usuarioService;}

    @GetMapping(value = {"","/"}, params = {"!buscar"})
    public Page<Usuario> all(Pageable pageable) {
        log.info("Accediendo a todos los usuarios");
        return this.usuarioService.all(pageable);
    }

    @GetMapping({"","/"})
    public Page<Usuario> all(@RequestParam("buscar") String buscar,Pageable pageable) {
        log.info("Accediendo todos los usuario");
        return this.usuarioService.allByFilter(buscar,pageable);
    }

    @PostMapping({"","/"})
    public Usuario save(@RequestBody Usuario usuario){
        log.info("Guardando un usuario");
        return this.usuarioService.crearUsuario(usuario);
    }

    @GetMapping("/{id}")
    public Usuario one(@PathVariable("id") long id) {
        return this.usuarioService.one(id);
    }

    @PutMapping("/{id}")
    public Usuario replace(@PathVariable("id") long id, @RequestBody Usuario usuario) {
        return this.usuarioService.replace(id, usuario);

    }
    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/cambiarContrasenia")
    public void cambiarContrasenia(@RequestBody Usuario usuario, String newPassword) {
        String email = usuario.getEmail();
        String password = usuario.getPassword();

        if (!Objects.equals(password, newPassword)) {
            usuarioService.cambiarContrasenia(email, newPassword);
        } else {
            log.info("Las constraseñas son identicas");
        }


        log.info("La contraseña ha sido cambiada");
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") long id) {
        this.usuarioService.delete(id);
        log.info("El usuario con id" + id + " ha sido eliminado de la base de datos");
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping({"", "/"})
    public void deleteAll() {
        usuarioService.deleteAll();
        log.info("Todos los usuarios han sido eliminados de la base de datos");
    }


}
