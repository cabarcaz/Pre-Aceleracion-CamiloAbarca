package com.alkemy.peliculas.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Setter
public class PersonajeBasicDTO {
    private Long id;
    @NotEmpty(message = "Ingrese el nombre")
    private String nombre;
    private String imagen;
    private Integer edad;
    private Double peso;
    private String historia;
}
