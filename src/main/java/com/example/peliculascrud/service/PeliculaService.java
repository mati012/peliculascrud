package com.example.peliculascrud.service;

import com.example.peliculascrud.model.Pelicula;
import java.util.List;
import java.util.Optional;

public interface PeliculaService {
    List<Pelicula> getAllPeliculas();
    Optional<Pelicula> getPeliculaById(long id);
    Pelicula createPelicula(Pelicula pelicula);
    
}
