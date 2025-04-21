package com.nathan.springboot_libros.service;

import java.util.List;
import java.util.Optional;

import com.nathan.springboot_libros.dto.LibroDto;

public interface LibroService {

    List<LibroDto> getAllLibro();
    Optional<LibroDto> getLibroById(Long id);
    Optional<LibroDto> getLibroByTitulo(String alias);
    
    LibroDto createLibro(LibroDto usuario);
    LibroDto updateLibro(Long id, LibroDto usuario);
    void deleteLibro(Long id);


}
