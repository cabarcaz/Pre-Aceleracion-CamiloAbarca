package com.alkemy.peliculas.mapper;

import com.alkemy.peliculas.dto.PersonajeDTO;
import com.alkemy.peliculas.entity.PersonajeEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonajeMapper {

    public PersonajeEntity personajeDTO2Entity(PersonajeDTO dto){
        PersonajeEntity personajeEntity = new PersonajeEntity();
        personajeEntity.setNombre(dto.getNombre());
        personajeEntity.setEdad(dto.getEdad());
        personajeEntity.setImagen(dto.getImagen());
        personajeEntity.setRol(dto.getRol());
        personajeEntity.setHistoria(dto.getHistoria());
        //TODO: Pendiente enviar lista de peliculas.
        return personajeEntity;
    }

    public PersonajeDTO  personajeEntity2DTO(PersonajeEntity entity){
        PersonajeDTO dto = new PersonajeDTO()   ;
        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        dto.setEdad(entity.getEdad());
        dto.setImagen(entity.getImagen());
        dto.setRol(entity.getRol());
        dto.setHistoria(entity.getHistoria());
        //TODO: Pendiente traer la lista de peliculas.
        return  dto;
    }

    public List<PersonajeDTO> personajeEntityList2DTOlist(List<PersonajeEntity>entities){
        List<PersonajeDTO> dtos = new ArrayList<>();
        for (PersonajeEntity entity:entities){
            dtos.add(personajeEntity2DTO(entity));
        }
        return dtos;
    }


    private LocalDate string2LocalDate(String stringDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(("yyyy-MM-dd"));
        LocalDate date = LocalDate.parse(stringDate,formatter);
        return date;
    }
}
