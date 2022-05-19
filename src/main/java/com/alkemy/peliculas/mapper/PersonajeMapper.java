package com.alkemy.peliculas.mapper;

import com.alkemy.peliculas.dto.PeliculaDTO;
import com.alkemy.peliculas.dto.PersonajeBasicDTO;
import com.alkemy.peliculas.dto.PersonajeDTO;
import com.alkemy.peliculas.entity.PeliculaEntity;
import com.alkemy.peliculas.entity.PersonajeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class PersonajeMapper {

    @Autowired
    private PeliculaMapper peliculaMapper;

    public PersonajeEntity personajeDTO2Entity(PersonajeDTO dto){
        PersonajeEntity personajeEntity = new PersonajeEntity();
        personajeEntity.setId(dto.getId());
        personajeEntity.setNombre(dto.getNombre());
        personajeEntity.setImagen(dto.getImagen());
        personajeEntity.setEdad(dto.getEdad());
        personajeEntity.setPeso(dto.getPeso());
        personajeEntity.setHistoria(dto.getHistoria());
        //? Peliculas
        Set<PeliculaEntity> peliculas;
        peliculas = this.peliculaMapper.peliculaDTOList2Entity(dto.getPeliculas());
        personajeEntity.setPeliculas(peliculas);
        return personajeEntity;
    }

    public PersonajeDTO  personajeEntity2DTO(PersonajeEntity entity, Boolean loadPeliculas){
        PersonajeDTO dto = new PersonajeDTO()   ;
        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        dto.setEdad(entity.getEdad());
        dto.setImagen(entity.getImagen());
        dto.setPeso(entity.getPeso());
        dto.setHistoria(entity.getHistoria());
        if(loadPeliculas){
            List<PeliculaDTO> peliculaDTOS = this.peliculaMapper.peliculaEntitySet2DTOList(entity.getPeliculas(), false);
            dto.setPeliculas(peliculaDTOS);
        }
        return  dto;
    }

    public List<PersonajeDTO> personajeEntityList2DTOlist(List<PersonajeEntity>entities, boolean loadPeliculas){
        List<PersonajeDTO> dtos = new ArrayList<>();
        for (PersonajeEntity entity : entities){
            dtos.add(this.personajeEntity2DTO(entity,loadPeliculas));
        }
        return dtos;
    }

    public List<PersonajeEntity> personajeDTOList2EntityList(List<PersonajeDTO>dtos){
        List<PersonajeEntity> entities = new ArrayList<>();
        PersonajeEntity entity;
        for(PersonajeDTO dto : dtos){
            entity = new PersonajeEntity() ;
            entity.setId(dto.getId());
            entity.setNombre(dto.getNombre());
            entity.setPeso(dto.getPeso());
            entity.setHistoria(dto.getHistoria());
            entity.setEdad(dto.getEdad());
            entity.setImagen(dto.getImagen());
            entities.add(entity);
        }
        return entities;
    }

    public List<PersonajeBasicDTO> personajeEntityList2BasicDTOList(List<PersonajeEntity>dtos){
        List<PersonajeBasicDTO> basicDTOS   = new ArrayList<>() ;
        PersonajeBasicDTO basicDTO;
        for(PersonajeEntity entity : dtos){
            basicDTO = new PersonajeBasicDTO()  ;
            basicDTO.setId(entity.getId());
            basicDTO.setNombre(entity.getNombre());
            basicDTO.setImagen(entity.getImagen());
            basicDTOS.add(basicDTO);
        }
        return  basicDTOS;
    }
    public void personajeEntityRefreshValues(PersonajeEntity entity, PersonajeBasicDTO dto){
        entity.setImagen(dto.getImagen());
        entity.setNombre(dto.getNombre());
        entity.setId(dto.getId());
        entity.setEdad(dto.getEdad());
        entity.setHistoria(dto.getHistoria());
        entity.setPeso(dto.getPeso());

    }

}
