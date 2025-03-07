package com.iesvdm.proyectoapi.dataCharge;

import com.iesvdm.proyectoapi.domain.*;
import com.iesvdm.proyectoapi.enums.Rol;
import com.iesvdm.proyectoapi.repository.*;
import com.iesvdm.proyectoapi.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private AsignaturaRepository asignaturaRepository;

    @Autowired
    private CalendarioRepository calendarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private ExamenRepository examenRepository;

    @Autowired
    private TareaRepository tareaRepository;

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void run(String... args) {
        // 1. Cargando datos de Asignaturas
        Asignatura asignatura1 = new Asignatura();
        asignatura1.setNombre("Matemáticas");

        Asignatura asignatura2 = new Asignatura();
        asignatura2.setNombre("Física");

        Asignatura asignatura3 = new Asignatura();
        asignatura3.setNombre("Historia");
        
        asignaturaRepository.save(asignatura1);
        asignaturaRepository.save(asignatura2);
        asignaturaRepository.save(asignatura3);

        // 2. Cargando datos de Calendarios
        Calendario calendario1 = new Calendario();
        calendario1.setExamenes(new HashSet<>());
        calendario1.setTareas(new HashSet<>());

        Calendario calendario2 = new Calendario();
        calendario2.setExamenes(new HashSet<>());
        calendario2.setTareas(new HashSet<>());

        Calendario calendario3 = new Calendario();
        calendario3.setExamenes(new HashSet<>());
        calendario3.setTareas(new HashSet<>());

        calendarioRepository.save(calendario1);
        calendarioRepository.save(calendario2);
        calendarioRepository.save(calendario3);

        // 3. Cargando datos de Cursos

        Set<Asignatura> asignaturas1 = new HashSet<>();
        asignaturas1.add(asignatura1);
        asignaturas1.add(asignatura2);

        Set<Asignatura> asignaturas2 = new HashSet<>();
        asignaturas2.add(asignatura3);

        Set<Asignatura> asignaturas3 = new HashSet<>();
        asignaturas3.add(asignatura1);
        asignaturas3.add(asignatura3);

        Curso curso1 = new Curso();
        curso1.setNombre("Ingeniería");
        curso1.setAsignaturas(asignaturas1);

        Curso curso2 = new Curso();
        curso2.setNombre("Ciencias Sociales");
        curso2.setAsignaturas(asignaturas2);

        Curso curso3 = new Curso();
        curso3.setNombre("Humanidades");
        curso3.setAsignaturas(asignaturas3);
        
        cursoRepository.save(curso1);
        cursoRepository.save(curso2);
        cursoRepository.save(curso3);

        // 4. Cargando datos de Exámenes
        Examen examen1 = new Examen();
        examen1.setTitulo("Examen Parcial");
        examen1.setAsignatura(asignatura1);
        examen1.setFecha(LocalDateTime.now());

        Examen examen2 = new Examen();
        examen2.setTitulo("Examen Final");
        examen2.setAsignatura(asignatura2);
        examen2.setFecha(LocalDateTime.now().plusDays(15));

        Examen examen3 = new Examen();
        examen3.setTitulo("Examen de Recuperación");
        examen3.setAsignatura(asignatura3);
        examen3.setFecha(LocalDateTime.now().plusDays(30));

        examenRepository.save(examen1);
        examenRepository.save(examen2);
        examenRepository.save(examen3);

        // 5. Cargando datos de Tareas
        Tarea tarea1 = new Tarea();
        tarea1.setTitulo("Tarea 1");
        tarea1.setDescripcion("Resolver ejercicios de matemáticas");
        tarea1.setAsignatura(asignatura1);
        tarea1.setFechaEntrega(LocalDateTime.now().plusDays(7));

        Tarea tarea2 = new Tarea();
        tarea2.setTitulo("Tarea 2");
        tarea2.setDescripcion("Investigar fenómenos físicos");
        tarea2.setAsignatura(asignatura2);
        tarea2.setFechaEntrega(LocalDateTime.now().plusDays(10));

        Tarea tarea3 = new Tarea();
        tarea3.setTitulo("Tarea 3");
        tarea3.setDescripcion("Realizar análisis histórico");
        tarea3.setAsignatura(asignatura3);
        tarea3.setFechaEntrega(LocalDateTime.now().plusDays(14));

        tareaRepository.save(tarea1);
        tareaRepository.save(tarea2);
        tareaRepository.save(tarea3);
        

        // 6. Cargando datos de Usuarios
        Usuario usuario1 = new Usuario();
        usuario1.setNombre("alumno1");
        usuario1.setApellidos("Apellido1");
        usuario1.setEmail("alumno1@g.educaand.es");
        usuario1.setPassword("123456");
        usuario1.setRol(Rol.PROFESOR);
        usuario1.setCurso(curso1);


        Usuario usuario2 = new Usuario();
        usuario2.setNombre("alumno2");
        usuario2.setApellidos("Apellido2");
        usuario2.setEmail("alumno2@g.educaand.es");
        usuario2.setPassword("123456");
        usuario2.setRol(Rol.ALUMNO);
        usuario2.setCurso(curso2);

        Usuario usuario3 = new Usuario();
        usuario3.setNombre("alumno3");
        usuario3.setApellidos("Apellido3");
        usuario3.setEmail("alumno3@g.educaand.es");
        usuario3.setPassword("123456");
        usuario3.setRol(Rol.ALUMNO);
        usuario3.setCurso(curso3);

        usuarioService.crearUsuario(usuario1);
        usuarioService.crearUsuario(usuario2);
        usuarioService.crearUsuario(usuario3);

        System.out.println("Datos de prueba cargados correctamente.");
    }
}