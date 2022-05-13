package com.alkemy.peliculas.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PeliculaDTO {
    private Long id;
    private String imagen;
    private String titulo;
    private String fechaDeCreacion;
    private Long calificacion;
    private GeneroDTO genero;
    private List<PersonajeDTO> personajes;

}
