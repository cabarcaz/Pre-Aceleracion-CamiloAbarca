package com.alkemy.peliculas.mapper;

import com.alkemy.peliculas.dto.PeliculaDTO;
import com.alkemy.peliculas.entity.PeliculaEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


@Component
public class PeliculaMapper {

    public PeliculaEntity peliculaDTO2Entity(PeliculaDTO dto){
        PeliculaEntity peliculaEntity = new PeliculaEntity()   ;
        peliculaEntity.setImagen(dto.getImagen());
        peliculaEntity.setTitulo(dto.getTitulo());
        peliculaEntity.setCalificacion(dto.getCalificacion());
        //TODO: Pendiente gestionar y enviar fecha.
        //TODO: Pendiente enviar genero
        //TODO: Pendiente enviar personajes
        return  peliculaEntity;
    }

    public PeliculaDTO peliculaEntity2DTO(PeliculaEntity entity){
        PeliculaDTO dto = new PeliculaDTO();
        dto.setId(entity.getId());
        dto.setImagen(entity.getImagen());
        dto.setTitulo(entity.getTitulo());
        dto.setCalificacion(entity.getCalificacion());
        //TODO: Pendiente devolver Fecha
        //TODO: Pendieinte devolver genero
        //TODO: Pendiente devolver personajes.
        return  dto;
    }

    public List<PeliculaDTO> peliculaEntityList2DTOList(List<PeliculaEntity>entities){
        List<PeliculaDTO> dtos =new ArrayList<>();
        for(PeliculaEntity entity : entities){
            dtos.add(peliculaEntity2DTO(entity));
        }
        return dtos;
    }

    private LocalDate string2LocalDate(String stringDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(("yyyy-MM-dd"));
        LocalDate date = LocalDate.parse(stringDate,formatter);
        return date;
    }
}
