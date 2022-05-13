package com.alkemy.peliculas.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="personaje")
@Getter
@Setter
public class PersonajeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String imagen;

    private String nombre;

    private Long edad;

    private  String rol;

    private String historia;

    @ManyToMany(mappedBy = "personajes",cascade ={CascadeType.MERGE,CascadeType.PERSIST})
    private Set<PeliculaEntity> peliculas = new HashSet<>();
}
