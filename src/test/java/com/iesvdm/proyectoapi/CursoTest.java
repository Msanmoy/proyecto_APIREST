package com.iesvdm.proyectoapi;

import com.iesvdm.proyectoapi.domain.Asignatura;
import com.iesvdm.proyectoapi.domain.Curso;
import com.iesvdm.proyectoapi.repository.CursoRepository;
import com.iesvdm.proyectoapi.services.CursoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CursoTest {

    @Autowired
    private PlatformTransactionManager platformTransactionManager;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Autowired
    CursoService cursoService;

    @Autowired
    CursoRepository cursoRepository;

    @Test
    void guardarCurso() {
        transactionTemplate.executeWithoutResult(transactionStatus -> {
            Curso curso = new Curso();
            cursoService.crearCurso(curso);
            Optional<Curso> curso1 = cursoService.obtenerCursoPorId(curso.getId());
            assertTrue(curso1.isPresent());
            assertEquals(curso, curso1.get());
        });
    }


    @Test
    void obtenerCursoPorId() {
        transactionTemplate.executeWithoutResult(status -> {
            Curso curso = cursoService.obtenerCursos().getFirst();
            Optional<Curso> curso1 = cursoService.obtenerCursoPorId(curso.getId());

            assertNotNull(curso);
            assertTrue(curso1.isPresent());
            assertEquals(curso, curso1.get());
        });
    }

    @Test
    void obtenerCursos() {
        transactionTemplate.executeWithoutResult(transactionStatus -> {
            List<Curso> listaCurso = cursoService.obtenerCursos();
            assertEquals(3, listaCurso.size());
        });
    }

    @Test
    void eliminarCursoPorId() {
        transactionTemplate.executeWithoutResult(transactionStatus -> {
            Optional<Curso> curso= cursoService.obtenerCursoPorId(1L);
            assertTrue(curso.isPresent());

            cursoService.eliminarCurso(curso.get().getId());

            Optional<Curso> cursoPostBorrado = cursoService.obtenerCursoPorId(1L);
            assertFalse(cursoPostBorrado.isPresent());


            // ACTUALIZAR MERGE PERSIST
            // Preguntar Profesor


        });
    }
}
