package com.alkemy.peliculas.controller;


import com.alkemy.peliculas.dto.GeneroDTO;
import com.alkemy.peliculas.service.GeneroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("generos")
public class GeneroController {

    @Autowired
    private GeneroService generoService;

//! CRUD -> Create-Read-Update-Delete

    //? EndPoint Save/Create.
    @PostMapping
    public ResponseEntity<GeneroDTO> save(@RequestBody GeneroDTO genero){
        GeneroDTO generoGuardado = generoService.save(genero);
        //Respuesta del Status + objeto
        return ResponseEntity.status(HttpStatus.CREATED).body(generoGuardado);
    }

    //? End Point Read lista de generos.
    @GetMapping
    public ResponseEntity<List<GeneroDTO>> getAll(){
        List<GeneroDTO> generos = generoService.getAllGeneros();
        return ResponseEntity.ok().body(generos);
    }

    //? End Point Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        generoService.delete(id);
        return  ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }




    }





