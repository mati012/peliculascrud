package com.example.peliculascrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.peliculascrud.model.Pelicula;

public interface PeliculaRepository extends JpaRepository<Pelicula, Long> {

    
}