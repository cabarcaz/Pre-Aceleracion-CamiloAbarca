package com.alkemy.peliculas.controller;

import com.alkemy.peliculas.dto.PeliculaDTO;
import com.alkemy.peliculas.service.PeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("peliculas")
public class PeliculaController {

    @Autowired
    private PeliculaService peliculaService;

    //? End Pont Save
    @PostMapping
    public ResponseEntity<PeliculaDTO> save(@RequestBody PeliculaDTO pelicula){
        return null;
    }

}
