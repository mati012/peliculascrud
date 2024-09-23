package com.example.peliculascrud.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.peliculascrud.model.Pelicula;
import com.example.peliculascrud.repository.PeliculaRepository;

@ExtendWith(MockitoExtension.class)
public class PeliculaServiceTest {
    @InjectMocks
    private PeliculaServiceImpl peliculaServicio;

    @Mock
    private PeliculaRepository peliculaRepositoryMock;

    @Test
    public void guardarEstudianteTest() {

        Pelicula pelicula = new Pelicula();
        pelicula.setTitulo("Jose Rondon");

        when(peliculaRepositoryMock.save(any())).thenReturn(pelicula);

        Pelicula resultado = peliculaServicio.createPelicula(pelicula);

        assertEquals("Jose Rondon", resultado.getTitulo());
    }

    @Test
    public void testGetPeliculaById_Found() {
       
        Pelicula pelicula = new Pelicula();
        pelicula.setId(1L);
        when(peliculaRepositoryMock.findById(1L)).thenReturn(Optional.of(pelicula));

        
        Optional<Pelicula> result = peliculaServicio.getPeliculaById(1L);

   
        assertTrue(result.isPresent(), "Se encontró la película con el ID: " + pelicula.getId());
        
    }


}