package com.example.peliculascrud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.peliculascrud.model.Pelicula;
import com.example.peliculascrud.service.PeliculaService;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.hateoas.CollectionModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/peliculas")
public class PeliculaController {
 
    @Autowired
    private PeliculaService peliculaService;

    @GetMapping
    public CollectionModel<EntityModel<Pelicula>> getAllPeliculas() {
        List<Pelicula> peliculas = peliculaService.getAllPeliculas();
        
   
        List<EntityModel<Pelicula>> peliculaModels = peliculas.stream()
            .map(pelicula -> EntityModel.of(pelicula, 
                WebMvcLinkBuilder.linkTo(methodOn(PeliculaController.class).getPeliculaById(pelicula.getId())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(methodOn(PeliculaController.class).getAllPeliculas()).withRel("all-peliculas")))
            .toList();
        
        // Return the CollectionModel with all EntityModels and a link to the list
        return CollectionModel.of(peliculaModels, 
            WebMvcLinkBuilder.linkTo(methodOn(PeliculaController.class).getAllPeliculas()).withSelfRel());
    }

    @GetMapping("/{id}")
    public EntityModel<Pelicula> getPeliculaById(@PathVariable Long id) {
        Optional<Pelicula> pelicula = peliculaService.getPeliculaById(id);
        
        if (pelicula.isPresent()) {
            return EntityModel.of(pelicula.get(), 
                WebMvcLinkBuilder.linkTo(methodOn(PeliculaController.class).getPeliculaById(id)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(methodOn(PeliculaController.class).getAllPeliculas()).withRel("all-peliculas"));
        } else {
          
            throw new PeliculaNotFoundException("Pelicula not found with id: " + id);
        }
    }

    @PostMapping
    public EntityModel<Pelicula> createPelicula(@RequestBody Pelicula pelicula) {
        Pelicula newPelicula = peliculaService.createPelicula(pelicula);
        
        return EntityModel.of(newPelicula, 
            WebMvcLinkBuilder.linkTo(methodOn(PeliculaController.class).getPeliculaById(newPelicula.getId())).withSelfRel(),
            WebMvcLinkBuilder.linkTo(methodOn(PeliculaController.class).getAllPeliculas()).withRel("all-peliculas"));
    }
}
