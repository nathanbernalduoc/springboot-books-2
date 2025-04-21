package com.nathan.springboot_libros.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "NB_LIBRO")

public class LibroDto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "libro_secuence")
    @SequenceGenerator(name = "libro_secuence", sequenceName = "NB_LIBRO_LIBRO_ID_SEQ", allocationSize = 1)
    @Column(name = "LIBRO_ID")
    private Long libroId;
    @Column(name = "TITULO")
    private String titulo;
    @Column(name = "AUTOR_NOMBRES")
    private String autor;
    @Column(name = "PUBLICACION_ANIO")
    private int publicacion;
    @Column(name = "GENERO")
    private String genero;

    LibroDto() {

    }

    LibroDto(Long libroId, String titulo, int publicacion, String genero) {
        this.libroId = libroId;
        this.titulo = titulo;
        this.publicacion = publicacion;
        this.genero = genero;
    }

    public Long getLibroId() {
        return libroId;
    }

    public void setLibroId(Long libroId) {
        this.libroId = libroId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(int publicacion) {
        this.publicacion = publicacion;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

}
