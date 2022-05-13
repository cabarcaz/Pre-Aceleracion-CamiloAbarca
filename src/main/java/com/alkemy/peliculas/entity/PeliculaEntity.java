package com.alkemy.peliculas.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="pelicula")
@Getter
@Setter
@SQLDelete(sql = "UPDATE pelicula SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class PeliculaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String imagen;
    private String titulo;
    private Long calificacion;
    private Boolean deleted = Boolean.FALSE;

    @DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
    @Column(name="fecha_de_creacion")
    private LocalDateTime fechaDeCreacion;

    @ManyToOne
    @JoinColumn(name ="genero_id", referencedColumnName = "id")
    private GeneroEntity genero;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "personaje_pelicula",
            joinColumns = @JoinColumn(name = "pelicula_id",nullable = false),
            inverseJoinColumns = @JoinColumn(name = "personaje_id",nullable = false),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"pelicula_id","personaje_id"})})
    private List<PersonajeEntity> personajes = new ArrayList<>();

    public void addPersonaje(PersonajeEntity personaje){
        this.personajes.add(personaje);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PeliculaEntity pelicula = (PeliculaEntity) o;

        if (Double.compare(pelicula.calificacion, calificacion) != 0) return false;
        if (!Objects.equals(id, pelicula.id)) return false;
        return Objects.equals(personajes, pelicula.personajes);
    }


    public void removePersonaje(PersonajeEntity personaje){
        this.personajes.remove(personaje);

    }



}
