/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.api.musiclab.repository;

import com.api.musiclab.entities.Cesta;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author davidlema
 */
public interface CestaRepository extends JpaRepository <Cesta, Long> {
    
    // Obtener la cesta de un usuario
    Optional<Cesta> findByUserId(Long userId);
    
    // Saber si un usuario ya tiene cesta
    boolean existsByUserId(Long userId);
    
    // Obtener cesta + líneas en una sola query
    @Query("SELECT c FROM Cesta c LEFT JOIN FETCH c.lineas WHERE c.userId = :userId")
    Optional<Cesta> findByUserIdWithLineas(@Param("userId") Long userId);
    
    @Query("SELECT c FROM Cesta c LEFT JOIN FETCH c.lineas WHERE c.id = :id")
    Optional<Cesta> findByIdWithLineas(@Param("id") Long id);
    
}
