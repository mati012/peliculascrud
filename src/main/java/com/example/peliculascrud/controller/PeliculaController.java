package com.example.peliculascrud.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.peliculascrud.model.Pelicula;
import com.example.peliculascrud.service.PeliculaService;

import java.util.List;
import java.util.Optional;

import javax.swing.text.html.parser.Entity;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;




@RestController
@RequestMapping("/peliculas")
public class PeliculaController {

    @Autowired
    private PeliculaService peliculaService;

    @GetMapping
    public  List<Pelicula> getAllPeliculas() {
        return peliculaService.getAllPeliculas();
    }


    @GetMapping("/{id}")
    public  Optional<Pelicula> getPeliculaById(@PathVariable Long id) {
        return peliculaService.getPeliculaById(id);
    }


    @PostMapping
    public Pelicula createPelicula(@RequestBody Pelicula pelicula) {
        return peliculaService.createPelicula(pelicula); 
    }
    

}