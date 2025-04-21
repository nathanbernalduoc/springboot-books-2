package com.nathan.springboot_libros.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class LibroNotFoundExcepcion extends RuntimeException {

    public LibroNotFoundExcepcion(String message) {
        super(message);
    }
}
