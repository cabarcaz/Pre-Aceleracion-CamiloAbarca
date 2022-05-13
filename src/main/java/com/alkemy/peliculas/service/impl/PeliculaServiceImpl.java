package com.alkemy.peliculas.service.impl;

import com.alkemy.peliculas.dto.PeliculaDTO;
import com.alkemy.peliculas.entity.PeliculaEntity;
import com.alkemy.peliculas.mapper.PeliculaMapper;
import com.alkemy.peliculas.repository.PeliculaRepository;
import com.alkemy.peliculas.service.PeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeliculaServiceImpl implements PeliculaService {
    @Autowired
    private PeliculaMapper peliculaMapper;
    @Autowired
    private PeliculaRepository peliculaRepository;

    @Override
    public PeliculaDTO save(PeliculaDTO dto) {
        PeliculaEntity entity = peliculaMapper.peliculaDTO2Entity(dto);
        PeliculaEntity entitySaved = peliculaRepository.save(entity);
        PeliculaDTO result = peliculaMapper.peliculaEntity2DTO(entitySaved);
        return result;
    }

    @Override
    public List<PeliculaDTO> getAllPeliculas() {
        List<PeliculaEntity> entities= peliculaRepository.findAll();
        List<PeliculaDTO> result = peliculaMapper.peliculaEntityList2DTOList(entities);
        return result;
    }

    @Override
    public void delete(Long id) {
        peliculaRepository.deleteById(id);
    }
}
