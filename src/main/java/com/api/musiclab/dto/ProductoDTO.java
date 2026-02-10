/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.api.musiclab.dto;

import com.api.musiclab.entities.Producto;

/**
 *
 * @author danig
 */
public class ProductoDTO {
    
    private Long id;
    private String nombre;
    private String marca;
    private Double precio;
    private Integer stock;
    private String descripcion;
    private String imagen;
    private Long subCategoriaId;

    public ProductoDTO(Producto p) {
        this.id = p.getId();
        this.nombre = p.getNombre();
        this.marca = p.getMarca();
        this.precio = p.getPrecio();
        this.stock = p.getStock();
        this.descripcion = p.getDescripcion();
        this.imagen = p.getImagen();
        this.subCategoriaId = p.getSubCategoria().getId();
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getMarca() {
        return marca;
    }

    public Double getPrecio() {
        return precio;
    }

    public Integer getStock() {
        return stock;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Long getSubCategoriaId() {
        return subCategoriaId;
    }
    
}
