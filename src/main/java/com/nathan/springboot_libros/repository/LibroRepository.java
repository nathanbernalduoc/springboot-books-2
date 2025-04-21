package com.nathan.springboot_libros.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nathan.springboot_libros.dto.LibroDto;

public interface LibroRepository extends JpaRepository<LibroDto, Long> {

}
