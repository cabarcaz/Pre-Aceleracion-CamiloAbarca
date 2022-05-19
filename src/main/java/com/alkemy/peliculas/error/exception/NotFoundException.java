package com.alkemy.peliculas.error.exception;

import org.hibernate.type.StringNVarcharType;

public class NotFoundException extends RuntimeException{
    private static final String DESCRIPTION = "Not Found Exception";

    public  NotFoundException(String detail){
        super(DESCRIPTION + ", " + detail);
    }
}
