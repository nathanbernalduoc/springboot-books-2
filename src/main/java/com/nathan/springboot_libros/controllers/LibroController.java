package com.nathan.springboot_libros.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nathan.springboot_libros.dto.LibroDto;
import com.nathan.springboot_libros.service.LibroService;

@RestController
@RequestMapping("/libros")
public class LibroController {

    @Autowired
    private LibroService libroService;

    @GetMapping
    public CollectionModel<EntityModel<LibroDto>> getAllLibros() {

        List<LibroDto> libros = libroService.getAllLibro();
        List<EntityModel<LibroDto>> libroResource = 
            libros.stream().map(
                libro -> EntityModel.of(
                    libro,
                    WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder.methodOn(
                            this.getClass()
                        ).getLibroById(libro.getLibroId())
                    ).withSelfRel()
                )
            )
            .collect(Collectors.toList());

        WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllLibros());
        CollectionModel<EntityModel<LibroDto>> resources = CollectionModel.of(libroResource, linkTo.withRel("libros"));

        return resources;
    }

    @GetMapping("/{id}")
    public EntityModel<LibroDto> getLibroById(@PathVariable Long id) {
        Optional<LibroDto> libro = libroService.getLibroById(id);

        if (libro.isPresent()) {
            return EntityModel.of(libro.get(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getLibroById(id)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllLibros()).withRel("all-libros"));
        } else {
            throw new LibroNotFoundExcepcion("El libro no ha sido encontrado");
        }

    }

    @PostMapping
    public EntityModel<LibroDto> createLibro(@RequestBody LibroDto libro) {
        LibroDto createdLibro = libroService.createLibro(libro);

        return EntityModel.of(createdLibro,
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getLibroById(createdLibro.getLibroId())).withSelfRel(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllLibros()).withRel("all-libros"));

    }

    @GetMapping("/alias/{alias}")
    public EntityModel<LibroDto> getLibroAlias(@PathVariable String alias) {

        Optional<LibroDto> libro = libroService.getLibroByTitulo(alias);

        if (libro.isPresent()) {
            return EntityModel.of(libro.get(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getLibroById(libro.get().getLibroId())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllLibros()).withRel("all-libros"));
        } else {
            throw new LibroNotFoundExcepcion("El alias indicado no ha sido encontrado");
        }

    }

    @PutMapping({"/{id}"})
    public EntityModel<LibroDto> setLibro(@PathVariable Long id, @RequestBody LibroDto libro) {
        LibroDto updatedLibro = libroService.updateLibro(id, libro);
        return EntityModel.of(updatedLibro,
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getLibroById(id)).withSelfRel(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllLibros()).withRel("all-libros"));
    }

    @DeleteMapping({"/{id}"})
    public void deleteLibro(@PathVariable Long id) {
        libroService.deleteLibro(id);
    }

}
