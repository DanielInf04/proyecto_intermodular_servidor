/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.api.musiclab.controller;

import com.api.musiclab.dto.CategoriaRequest;
import com.api.musiclab.entities.Categoria;
import com.api.musiclab.repository.CategoriaRepository;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author davidlema
 */
@RestController
@RequestMapping("/api/categories")
public class CategoriaController {

    private final CategoriaRepository repository;

    public CategoriaController(CategoriaRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Categoria> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Categoria getById(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
    }

    @PostMapping
    public Categoria create(@Valid @RequestBody CategoriaRequest request) {

        // Validación: nombre duplicado
        if (repository.existsByNombreIgnoreCase(request.getNombre())) {
            throw new RuntimeException("Ya existe una categoría con ese nombre");
        }

        Categoria categoria = new Categoria();
        categoria.setNombre(request.getNombre().trim());

        return repository.save(categoria);
    }

    @PutMapping("/{id}")
    public Categoria update(@PathVariable Long id, @Valid @RequestBody CategoriaRequest request) {

        Categoria cat = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        // Validación: nombre duplicado (excepto si es la misma categoría)
        if (repository.existsByNombreIgnoreCase(request.getNombre())
                && !cat.getNombre().equalsIgnoreCase(request.getNombre())) {
            throw new RuntimeException("Ya existe otra categoría con ese nombre");
        }

        cat.setNombre(request.getNombre().trim());
        return repository.save(cat);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Categoría no encontrada");
        }

        repository.deleteById(id);
    }
}


