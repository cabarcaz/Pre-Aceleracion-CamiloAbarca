package com.alkemy.peliculas.controller;

import com.alkemy.peliculas.dto.PeliculaBasicDTO;
import com.alkemy.peliculas.dto.PeliculaDTO;
import com.alkemy.peliculas.error.exception.BadRequestException;
import com.alkemy.peliculas.service.PeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("peliculas")
public class PeliculaController {

    @Autowired
    private PeliculaService peliculaService;

    //? End Point Read Lista de generos.
    @GetMapping
    public ResponseEntity<List<PeliculaBasicDTO>> getDetailsByFilters(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Long genero,
            @RequestParam(required = false,
            defaultValue =  "ASC") String order
    ){
        List<PeliculaBasicDTO> peliculasBasic = this.peliculaService.getByFilters(name,genero,order);
        return ResponseEntity.ok().body(peliculasBasic);
    }

    //? End Pont Save/Create.
    @PostMapping
    public ResponseEntity<PeliculaDTO> save(@Valid @RequestBody PeliculaDTO pelicula) throws Exception{
        PeliculaDTO peliculaGuardada = this.peliculaService.save(pelicula);
        return ResponseEntity.status(HttpStatus.CREATED).body(peliculaGuardada);
    }

    @PostMapping("/{idMovie}/characters/{idCharacter}")
    public ResponseEntity<PeliculaDTO> addCharacter(@PathVariable Long idMovie ,
                                                    @PathVariable Long idCharacter) {
        PeliculaDTO result = this.peliculaService.addCharacter(idMovie,idCharacter);
        return ResponseEntity.ok().body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PeliculaDTO> update(@Valid @RequestBody PeliculaBasicDTO dto, BindingResult result,
                                              @PathVariable Long id){
        if(result.hasErrors()){
            throw new BadRequestException(result);
        }
        PeliculaDTO peliculaDTO = this.peliculaService.update(id, dto);
        return ResponseEntity.ok().body(peliculaDTO);
    }

    //? End Point Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        peliculaService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build() ;
    }
    @DeleteMapping("/{idMovie}/characters/{idCharacter}")
    public ResponseEntity<PeliculaDTO> removeCharacter(@PathVariable Long idMovie ,
                                                       @PathVariable Long idCharacter) {
        PeliculaDTO result = this.peliculaService.removeCharacter(idMovie,idCharacter);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
