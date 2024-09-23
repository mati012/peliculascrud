package com.example.peliculascrud.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.example.peliculascrud.model.Pelicula;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PeliculaRepositoryTest {

    @Autowired
    private PeliculaRepository peliculaRepository;

    // 1. Test para guardar una película
    @Test
    @Rollback(false)
    public void testCreatePelicula() {
        // Arrange
        Pelicula pelicula = new Pelicula();
        pelicula.setTitulo("Matrix");
        pelicula.setDirector("Wachowski");

        // Act
        Pelicula savedPelicula = peliculaRepository.save(pelicula);

        // Assert
        assertNotNull(savedPelicula);
        assertNotNull(savedPelicula.getId());
        assertEquals("Matrix", savedPelicula.getTitulo());
    }

    // 2. Test para buscar una película por ID
    @Test
    public void testFindPeliculaById() {
        // Arrange
        Pelicula pelicula = new Pelicula();
        pelicula.setTitulo("Inception");
        pelicula.setDirector("Christopher Nolan");

        Pelicula savedPelicula = peliculaRepository.save(pelicula);

        // Act
        Optional<Pelicula> foundPelicula = peliculaRepository.findById(savedPelicula.getId());

        // Assert
        assertNotNull(foundPelicula);
        assertEquals("Inception", foundPelicula.get().getTitulo());
    }
}
