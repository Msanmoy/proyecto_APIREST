package com.iesvdm.proyectoapi;

import com.iesvdm.proyectoapi.domain.Curso;
import com.iesvdm.proyectoapi.domain.Tarea;
import com.iesvdm.proyectoapi.repository.CursoRepository;
import com.iesvdm.proyectoapi.repository.TareaRepository;
import com.iesvdm.proyectoapi.services.CursoService;
import com.iesvdm.proyectoapi.services.TareaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TareaTest {

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Autowired
    TareaService tareaService;

    @Autowired
    TareaRepository tareaRepository;

    @Test
    void guardarTarea() {
        transactionTemplate.executeWithoutResult(transactionStatus -> {
            Tarea tarea = new Tarea();
            tareaService.crearTarea(tarea);
            Optional<Tarea> tarea1 = tareaService.obtenerTareaPorId(tarea.getId());
            assertTrue(tarea1.isPresent());
            assertEquals(tarea, tarea1.get());
        });
    }


    @Test
    void obtenerTareaPorId() {
        transactionTemplate.executeWithoutResult(status -> {
            Tarea tarea = tareaService.obtenerTareas().getFirst();
            Optional<Tarea> tarea1 = tareaService.obtenerTareaPorId(tarea.getId());

            assertNotNull(tarea);
            assertTrue(tarea1.isPresent());
            assertEquals(tarea, tarea1.get());
        });
    }

    @Test
    void obtenerTareas() {
        transactionTemplate.executeWithoutResult(transactionStatus -> {
            List<Tarea> listaTareas = tareaService.obtenerTareas();
            assertEquals(3, listaTareas.size());
        });
    }

    @Test
    void eliminarTareaPorId() {
        transactionTemplate.executeWithoutResult(transactionStatus -> {
            Optional<Tarea> tarea= tareaService.obtenerTareaPorId(1L);
            assertTrue(tarea.isPresent());

            tareaService.eliminarTarea(tarea.get().getId());

            Optional<Tarea> tareaPostBorrado = tareaService.obtenerTareaPorId(1L);
            assertTrue(tareaPostBorrado.isPresent());


            // ACTUALIZAR MERGE PERSIST
            // Preguntar Profesor


        });
    }
}
