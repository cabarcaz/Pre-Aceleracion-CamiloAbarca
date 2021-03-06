package com.alkemy.peliculas.service.impl;

import com.alkemy.peliculas.dto.PeliculaBasicDTO;
import com.alkemy.peliculas.dto.PeliculaDTO;
import com.alkemy.peliculas.dto.filters.PeliculaFiltersDTO;
import com.alkemy.peliculas.entity.PeliculaEntity;
import com.alkemy.peliculas.entity.PersonajeEntity;
import com.alkemy.peliculas.error.exception.NotFoundException;
import com.alkemy.peliculas.mapper.PeliculaMapper;
import com.alkemy.peliculas.repository.PeliculaRepository;
import com.alkemy.peliculas.repository.PersonajeRepository;
import com.alkemy.peliculas.repository.specifications.PeliculaSpecification;
import com.alkemy.peliculas.service.PeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PeliculaServiceImpl implements PeliculaService {
    @Autowired
    private PeliculaMapper peliculaMapper;
    @Autowired
    private PeliculaRepository peliculaRepository;
    @Autowired
    private PeliculaSpecification peliculaSpecification;
    @Autowired
    private PersonajeRepository personajeRepository;

    @Value("${pelicula.addCharacterErrorMsj}")
    private String addCharacterErrorMsj;
    @Value("${pelicula.removeCharacterErrorMsj}")
    private String removeCharacterErrorMsj;
    @Value("${pelicula.idErrorMsj}")
    private String idErrorMsj;

    @Transactional
    @Override
    public PeliculaDTO save(PeliculaDTO dto) {
        PeliculaEntity entity = this.peliculaMapper.peliculaDTO2Entity(dto);
        PeliculaEntity entitySaved = this.peliculaRepository.save(entity);
        PeliculaDTO result = this.peliculaMapper.peliculaEntity2DTO(entitySaved,true);
        return result;
    }
    @Transactional
    @Override
    public PeliculaDTO update(Long idPelicula, PeliculaBasicDTO basicDTO) {
        Optional<PeliculaEntity> entity = this.peliculaRepository.findById(idPelicula);
        if (!entity.isPresent()) {
            throw new NotFoundException(idErrorMsj + idPelicula);
        }
        this.peliculaMapper.peliculaEntityRefreshValues(entity.get(), basicDTO);
        PeliculaEntity entitySaved = this.peliculaRepository.save(entity.get());
        PeliculaDTO result = this.peliculaMapper.peliculaEntity2DTO(entitySaved, true);
        return result;
    }
    @Transactional
    @Override
    public void delete(Long idPelicula) {
        Optional<PeliculaEntity> entity = this.peliculaRepository.findById(idPelicula);
        if (entity.isPresent()) {
            throw new NoSuchElementException(idErrorMsj + idPelicula);
        }
        this.peliculaRepository.delete(entity.get());
    }

    @Transactional(readOnly = true)
    @Override
    public List<PeliculaBasicDTO> getByFilters(String name, Long genre, String order) {
        PeliculaFiltersDTO filtersDTO = new PeliculaFiltersDTO(name, genre, order);
        List<PeliculaEntity> entities = this.peliculaRepository.findAll(this.peliculaSpecification.getByFilters(filtersDTO));
        List<PeliculaBasicDTO> basicDTOS = this.peliculaMapper.peliculaEntitySet2BasicDTOList(entities);
        return basicDTOS;
    }

    @Override
    public PeliculaDTO addCharacter(Long idPelicula, Long idPersonaje) {
        PersonajeEntity entityPersonaje = personajeRepository.findById(idPersonaje).orElse(null);
        PeliculaEntity entityPelicula = peliculaRepository.findById(idPelicula).orElse(null);

        if (entityPersonaje == null || entityPelicula == null || entityPelicula.getPersonajes().contains(entityPersonaje) ) {
            throw new NotFoundException(addCharacterErrorMsj);
        }
        entityPelicula.getPersonajes().add(entityPersonaje);

        PeliculaEntity entitySaved = peliculaRepository.save(entityPelicula);
        PeliculaDTO result = peliculaMapper.peliculaEntity2DTO(entitySaved, true);
        return result;
    }

    @Override
    public PeliculaDTO removeCharacter(Long idPelicula, Long idPersonaje) {
        PersonajeEntity entityPersonaje = personajeRepository.findById(idPersonaje).orElse(null);
        PeliculaEntity entityPelicula = peliculaRepository.findById(idPelicula).orElse(null);

        if (entityPersonaje == null || entityPelicula == null) {
            throw new NotFoundException(removeCharacterErrorMsj + idPersonaje);
        }
        entityPelicula.getPersonajes().remove(entityPersonaje);

        PeliculaEntity entitySaved = peliculaRepository.save(entityPelicula);
        PeliculaDTO result = peliculaMapper.peliculaEntity2DTO(entitySaved, true);
        return result;
    }
}
