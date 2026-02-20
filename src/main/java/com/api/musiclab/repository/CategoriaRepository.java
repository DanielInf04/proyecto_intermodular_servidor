/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.api.musiclab.repository;

import com.api.musiclab.entities.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author davidlema
 */
public interface CategoriaRepository extends JpaRepository <Categoria,Long> {
    boolean existsByNombreIgnoreCase(String nombre);

}
