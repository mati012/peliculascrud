package com.example.peliculascrud.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.test.web.servlet.MockMvc;

import com.example.peliculascrud.model.Pelicula;
import com.example.peliculascrud.service.PeliculaService;

@ExtendWith(MockitoExtension.class)
public class PeliculaControllerTest {

    @Mock
    private PeliculaService peliculaService;

    @InjectMocks
    private PeliculaController peliculaController;

    @Test
    public void testGetAllPeliculas() {

        Pelicula pelicula1 = new Pelicula();
        pelicula1.setId(1L);
        Pelicula pelicula2 = new Pelicula();
        pelicula2.setId(2L);
        List<Pelicula> peliculas = Arrays.asList(pelicula1, pelicula2);
        
        when(peliculaService.getAllPeliculas()).thenReturn(peliculas);

        
        CollectionModel<EntityModel<Pelicula>> result = peliculaController.getAllPeliculas();

        assertEquals(2, result.getContent().size());
        
        result.getContent().forEach(entityModel -> {
            assertTrue(entityModel.hasLink("self"));
        });

        assertTrue(result.hasLink("self"));
        verify(peliculaService).getAllPeliculas();
    }

    @Test
    public void testGetPeliculaById_Found() {

        Pelicula pelicula = new Pelicula();
        pelicula.setId(1L);
        
        when(peliculaService.getPeliculaById(1L)).thenReturn(Optional.of(pelicula));

    
        EntityModel<Pelicula> result = peliculaController.getPeliculaById(1L);

        assertTrue(result.hasLink("self")); 
        assertTrue(result.hasLink("all-peliculas")); 

        verify(peliculaService).getPeliculaById(1L);
    }
}
