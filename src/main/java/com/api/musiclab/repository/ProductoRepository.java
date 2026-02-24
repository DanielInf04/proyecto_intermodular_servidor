/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.api.musiclab.repository;

import com.api.musiclab.dto.ProductoDTO;
import com.api.musiclab.entities.Producto;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author danig
 */
public interface ProductoRepository extends JpaRepository<Producto, Long>{
    
    // Buscar producto por nombre
    /*@Query("""
           SELECT p FROM Producto p
           LEFT JOIN p.subCategoria sc
           WHERE LOWER(p.nombre) LIKE LOWER(CONCAT('%', :q, '%'))
              OR LOWER(sc.nombre) LIKE LOWER(CONCAT('%', :q, '%'))
           """)
    Page<Producto> search(@Param("q") String q, Pageable pageable);*/
    
    @Query(value = "SELECT * FROM producto ORDER BY RAND() LIMIT :limit", nativeQuery = true)
    List<Producto> findRandom(@Param("limit") int limit);
    
    @Query("""
        SELECT p
        FROM Producto p
        LEFT JOIN p.subCategoria sc
        LEFT JOIN sc.categoria c
        WHERE (
              :q IS NULL OR
              LOWER(p.nombre) LIKE LOWER(CONCAT('%', :q, '%')) OR
              LOWER(sc.nombre) LIKE LOWER(CONCAT('%', :q, '%'))
        )
        AND (:cId IS NULL OR c.id = :cId)
        AND (:scId IS NULL OR sc.id = :scId)
        AND (:minStock IS NULL OR p.stock >= :minStock)
        AND (:maxStock IS NULL OR p.stock <= :maxStock)
    """)
    Page<Producto> search(
        @Param("q") String q,
        @Param("cId") Long cId,
        @Param("scId") Long scId,
        @Param("minStock") Integer minStock,
        @Param("maxStock") Integer maxStock,
        Pageable pageable
    );

    // 
    
    List<Producto> findBySubCategoriaId(Long subCategoriaId);
     
    List<Producto> findByStockGreaterThan(int stock);
     
    List<Producto> findByPrecioBetween(double min, double max);
}
