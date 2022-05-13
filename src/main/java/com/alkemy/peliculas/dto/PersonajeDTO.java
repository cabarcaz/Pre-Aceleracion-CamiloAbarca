package com.alkemy.peliculas.dto;

import com.alkemy.peliculas.entity.PeliculaEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PersonajeDTO {

    private Long id;
    private String imagen;
    private String nombre;
    private Long edad;
    private String rol;
    private String historia;
    private List<PeliculaDTO> peliculas;
}
