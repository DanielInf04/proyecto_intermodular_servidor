/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.api.musiclab.dto;

import java.time.LocalDate;

/**
 *
 * @author davidlema
 */
public class UsuarioDTO {

    private Long id;
    private String username;
    private String email;
    private LocalDate fechaAlta;

    public UsuarioDTO(Long id, String username, String email, LocalDate fechaAlta) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.fechaAlta = fechaAlta;
    }

    // getters
    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }
}

