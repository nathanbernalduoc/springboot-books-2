package com.nathan.springboot_libros.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nathan.springboot_libros.dto.UsuarioDto;

public interface UsuarioRepository extends JpaRepository<UsuarioDto, Long> {
    
}
