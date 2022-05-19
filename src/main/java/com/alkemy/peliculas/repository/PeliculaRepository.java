package com.alkemy.peliculas.repository;

import com.alkemy.peliculas.entity.PeliculaEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.HTMLDocument;
import java.util.List;

@Repository
public interface PeliculaRepository extends JpaRepository<PeliculaEntity,Long>, JpaSpecificationExecutor<PeliculaEntity> {
    List<PeliculaEntity> findAll(Specification<PeliculaEntity> spec);
}
