package com.example.peliculascrud.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.peliculascrud.model.Pelicula;
import com.example.peliculascrud.repository.PeliculaRepository;

import java.util.List;
import java.util.Optional;

@Service

public class PeliculaServiceImpl implements PeliculaService {

    @Autowired
    private PeliculaRepository peliculaRepository;

    @Override
     public List<Pelicula> getAllPeliculas(){
        return peliculaRepository.findAll();
     }
    
    @Override
    public Optional<Pelicula> getPeliculaById(long id){
        return peliculaRepository.findById(id);

    }

    @Override
    public Pelicula createPelicula(Pelicula pelicula){
        return peliculaRepository.save(pelicula);
    }
}
