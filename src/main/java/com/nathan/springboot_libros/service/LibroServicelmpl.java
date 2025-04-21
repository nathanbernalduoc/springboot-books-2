package com.nathan.springboot_libros.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nathan.springboot_libros.dto.LibroDto;
import com.nathan.springboot_libros.repository.LibroRepository;

@Service
public class LibroServicelmpl implements LibroService {

    @Autowired
    private LibroRepository libroRepository;
    
    @Override
    public List<LibroDto> getAllLibro() {
        return libroRepository.findAll();
    }

    @Override
    public Optional<LibroDto> getLibroById(Long id) {
        return libroRepository.findById(id);
    }

    @Override
    public Optional<LibroDto> getLibroByTitulo(String titulo) {
        List<LibroDto> libros = libroRepository.findAll();
        Optional<LibroDto> libroSel = null;
        for(LibroDto libro: libros) {
            if (libro.getTitulo().equals(titulo)) {
                libroSel = Optional.of(libro);
            }
        }
        return libroSel;
    }

    @Override
    public LibroDto createLibro(LibroDto libro) {
        return libroRepository.save(libro);
    }

    @Override
    public LibroDto updateLibro(Long id, LibroDto libro) {
        if (libroRepository.existsById(id)) {
            libro.setLibroId(id);
            return libroRepository.save(libro);
        } else {
            return null;
        }
    }

    @Override
    public void deleteLibro(Long id) {
        libroRepository.deleteById(id);
    }
}
