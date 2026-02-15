/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.api.musiclab.dto;

import com.api.musiclab.entities.SubCategoria;

/**
 *
 * @author danig
 */
public class SubCategoriaDTO {
    private Long id;
    private String nombre;
    private String imagenUrl;
    private Long categoriaId;
    
    public SubCategoriaDTO(SubCategoria s) {
        this.id = s.getId();
        this.nombre = s.getNombre();
        this.imagenUrl = s.getImagenUrl();
        this.categoriaId = s.getCategoria().getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
    }
    
}
