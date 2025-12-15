package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataAccessException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

public class ShampooServiceTest {
    @Mock
    private ShampooRepositorio shampooRepositorio;

    @InjectMocks
    private ShampooService shampooService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testErroresLecturaYEscritura() {
        Shampoo shampoo = new Shampoo();
        shampoo.setNombre("Test");
        shampoo.setPrecio(10.0);
        shampoo.setStock(5);
        shampoo.setEstado(1);

        // Simula error de escritura
        when(shampooRepositorio.save(any())).thenThrow(new DataAccessException("Error de escritura") {});
        assertThrows(DataAccessException.class, () -> shampooService.crearShampoo("Test", 10.0, 5));

        // Simula error de lectura
        when(shampooRepositorio.findById(anyInt())).thenThrow(new DataAccessException("Error de lectura") {});
        assertThrows(DataAccessException.class, () -> shampooService.buscarShampoo(1));
    }
}

