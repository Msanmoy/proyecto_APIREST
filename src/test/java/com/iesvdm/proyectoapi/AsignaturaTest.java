package com.iesvdm.proyectoapi;

import com.iesvdm.proyectoapi.domain.Asignatura;
import com.iesvdm.proyectoapi.repository.AsignaturaRepository;
import com.iesvdm.proyectoapi.services.AsignaturaService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class AsignaturaTest {

    @Autowired
    private PlatformTransactionManager transactionManager;
    private TransactionTemplate transactionTemplate;

    @Autowired
    private AsignaturaRepository asignaturaRepository;

    @Autowired
    private AsignaturaService asignaturaService;

    @BeforeEach
    void setUp() {

        Asignatura asignatura1 = Asignatura.builder().nombre("Matemáticas").build();
        Asignatura asignatura2 = Asignatura.builder().nombre("Historia").build();
        Asignatura asignatura3 = Asignatura.builder().nombre("Ciencias").build();

        asignaturaRepository.save(asignatura1);
        asignaturaRepository.save(asignatura2);
        asignaturaRepository.save(asignatura3);

        transactionTemplate = new TransactionTemplate(transactionManager);

    }

    @Test
    void guardarAsignatura() {
        transactionTemplate.executeWithoutResult(transactionStatus -> {
            Asignatura asignatura = new Asignatura();
            asignaturaService.crearAsignatura(asignatura);
            Optional<Asignatura> asignatura1 = asignaturaService.obtenerAsignaturaPorId(asignatura.getId());
            assertTrue(asignatura1.isPresent());
            assertEquals(asignatura, asignatura1.get());
        });
    }


    @Test
    void obtenerAsignaturaPorId() {
        transactionTemplate.executeWithoutResult(status -> {
            Asignatura asignatura = asignaturaService.obtenerAsignaturas().getFirst();
            Optional<Asignatura> asignatura1 = asignaturaService.obtenerAsignaturaPorId(asignatura.getId());
            assertTrue(asignatura1.isPresent());
            assertNotNull(asignatura);
            assertEquals(asignatura, asignatura1.get());
        });
    }

    @Test
    void obtenerAsignaturas() {
        transactionTemplate.executeWithoutResult(transactionStatus -> {
            List<Asignatura> listaAsignaturas = asignaturaService.obtenerAsignaturas();
            assertEquals(13, listaAsignaturas.size());
        });
    }

    @Test
    void eliminarAsignaturaPorId() {
        transactionTemplate.executeWithoutResult(transactionStatus -> {
            Optional<Asignatura> asignatura= asignaturaService.obtenerAsignaturaPorId(1L);
            assertTrue(asignatura.isPresent());

            asignaturaService.eliminarAsignatura(asignatura.get().getId());

            Optional<Asignatura> asignaturaPostBorrado = asignaturaService.obtenerAsignaturaPorId(1L);
            assertFalse(asignaturaPostBorrado.isPresent());


            // ACTUALIZAR MERGE PERSIST
            // Preguntar Profesor


        });
    }
}
