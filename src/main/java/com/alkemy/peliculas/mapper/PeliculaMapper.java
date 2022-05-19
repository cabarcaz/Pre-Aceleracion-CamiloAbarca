package com.alkemy.peliculas.mapper;

import com.alkemy.peliculas.dto.PeliculaBasicDTO;
import com.alkemy.peliculas.dto.PeliculaDTO;
import com.alkemy.peliculas.dto.PersonajeDTO;
import com.alkemy.peliculas.entity.PeliculaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

import static com.alkemy.peliculas.mapper.util.UtilDate.string2LocalDate;

@Component
public class PeliculaMapper {

    @Autowired
    private PersonajeMapper personajeMapper;
    @Autowired
    private GeneroMapper generoMapper;

    public PeliculaEntity peliculaDTO2Entity(PeliculaDTO dto){
        PeliculaEntity peliculaEntity = new PeliculaEntity();
        peliculaEntity.setId(dto.getId());
        peliculaEntity.setImagen(dto.getImagen());
        peliculaEntity.setTitulo(dto.getTitulo());
        peliculaEntity.setCalificacion(dto.getCalificacion());
        peliculaEntity.setFechaDeCreacion(string2LocalDate(dto.getFechaDeCreacion()));
        peliculaEntity.setGenero(this.generoMapper.generoDTO2Entity(dto.getGenero()));
        peliculaEntity.setPersonajes(this.personajeMapper.personajeDTOList2EntityList(dto.getPersonajes()));
        return  peliculaEntity;
    }

    public PeliculaDTO peliculaEntity2DTO(PeliculaEntity entity, Boolean loadPersonajes){
        PeliculaDTO dto = new PeliculaDTO();
        dto.setId(entity.getId());
        dto.setImagen(entity.getImagen());
        dto.setTitulo(entity.getTitulo());
        dto.setCalificacion(entity.getCalificacion());
        dto.setFechaDeCreacion(entity.getFechaDeCreacion().toString());
        dto.setGenero(this.generoMapper.generoEntity2DTO(entity.getGenero()));
        if(loadPersonajes){
            List<PersonajeDTO> personajesDTO = this.personajeMapper.personajeEntityList2DTOlist(entity.getPersonajes(),false);
            dto.setPersonajes(personajesDTO);
        }
        return  dto;
    }

    public Set<PeliculaEntity> peliculaDTOList2Entity(List<PeliculaDTO>dtos){
        Set<PeliculaEntity> entities = new HashSet<>()  ;
        for(PeliculaDTO dto: dtos){
            entities.add(this.peliculaDTO2Entity(dto));
        }
        return entities;
    }

    public List<PeliculaDTO> peliculaEntitySet2DTOList(Collection<PeliculaEntity>entities,boolean loadPersosnajes){
        List<PeliculaDTO> dtos = new ArrayList<>() ;
        for(PeliculaEntity entity : entities){
            dtos.add(this.peliculaEntity2DTO(entity,loadPersosnajes));
        }
        return dtos;
    }

    public List<PeliculaBasicDTO> peliculaEntitySet2BasicDTOList(Collection<PeliculaEntity> entities){
        List<PeliculaBasicDTO> basicDTOS = new ArrayList<>();
        PeliculaBasicDTO basicDTO;
        for(PeliculaEntity entity : entities){
            basicDTO = new PeliculaBasicDTO();
            basicDTO.setId(entity.getId());
            basicDTO.setImagen(entity.getImagen());
            basicDTO.setTitulo(entity.getTitulo());
            basicDTO.setFechaDeCreacion(entity.getFechaDeCreacion().toString());
            basicDTOS.add(basicDTO);
        }
        return basicDTOS;
    }

    public void peliculaEntityRefreshValues(PeliculaEntity entity, PeliculaBasicDTO dto){
        entity.setImagen(dto.getImagen());
        entity.setTitulo(dto.getTitulo());
        entity.setFechaDeCreacion(string2LocalDate(dto.getFechaDeCreacion()));
    }


}
