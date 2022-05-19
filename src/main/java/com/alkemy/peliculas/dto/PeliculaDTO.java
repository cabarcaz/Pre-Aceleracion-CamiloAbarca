package com.alkemy.peliculas.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class PeliculaDTO {
    private Long id;
    private String imagen;
    @NotEmpty(message = "Ingrese el titulo")
    private String titulo;
    @NotNull(message = "Ingrese la fecha de creación")
    private String fechaDeCreacion;
    @Range(min=0,max=5, message = "El rango de calificación es entre 0 y 5")
    private Long calificacion;
    @NotNull(message = "Ingrese el genero de la pelicula")
    private GeneroDTO genero;
    private List<PersonajeDTO> personajes;

}
