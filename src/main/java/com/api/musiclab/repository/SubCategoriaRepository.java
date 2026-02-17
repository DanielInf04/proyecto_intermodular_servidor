/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.api.musiclab.repository;

import com.api.musiclab.entities.Producto;
import com.api.musiclab.entities.SubCategoria;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author danig
 */
public interface SubCategoriaRepository extends JpaRepository<SubCategoria, Long> {
    // Buscar una subcategoria por nombre
    Page<SubCategoria> findByNombreContainingIgnoreCase(String nombre, Pageable pageable);
    
    Page<SubCategoria> findByCategoriaIdAndNombreContainingIgnoreCase(
            Long categoriaId,
            String nombre,
            Pageable pageable
    );
    
    Page<SubCategoria> findByCategoriaId(Long categoriaId, Pageable pageable);
    
    List<SubCategoria> findByCategoriaId(Long categoriaId);
    
}
