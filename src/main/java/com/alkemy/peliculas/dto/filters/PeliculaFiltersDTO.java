package com.alkemy.peliculas.dto.filters;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PeliculaFiltersDTO {
    private String name;
    private Long genero;
    String order;

    public PeliculaFiltersDTO(String name, Long genero, String order){
        this.name = name;
        this.genero=genero;
        this.order=order;
    }
    public boolean isASC(){return this.order.compareToIgnoreCase("ASC")==0;}
    public boolean isDESC(){return this.order.compareToIgnoreCase("DESC")==0;}
}
