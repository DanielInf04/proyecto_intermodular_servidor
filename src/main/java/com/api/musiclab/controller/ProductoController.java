/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.api.musiclab.controller;

import com.api.musiclab.dto.ProductoDTO;
import com.api.musiclab.entities.Producto;
import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.api.musiclab.repository.ProductoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 *
 * @author danig
 */
@RestController
public class ProductoController {

    private final ProductoRepository repository;

    public ProductoController(ProductoRepository repository) {
        this.repository = repository;
    }
    
    @GetMapping("/api/products")
    public ResponseEntity<List<ProductoDTO>> listar(
            @RequestParam(name = "q", required = false) String q,
            @PageableDefault(size = 10) Pageable pageable
    ) {
        String baseUrl = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .build()
                .toUriString();
        
        Page<Producto> page;
        
        if (q != null && !q.isBlank()) {
            page = repository.search(q, pageable);
        } else {
            page = repository.findAll(pageable);
        }
        
        Page<ProductoDTO> dtoPage = page.map(ProductoDTO::new);
        
        dtoPage.getContent().forEach(sc -> {
            if (sc.getImagen() != null && sc.getImagen().startsWith("/")) {
                sc.setImagen(baseUrl + sc.getImagen());
            }
        });
        
        return ResponseEntity.ok()
                .header("X-Total-Count", String.valueOf(dtoPage.getTotalElements()))
                .header("X-Total-Pages", String.valueOf(dtoPage.getTotalPages()))
                .body(dtoPage.getContent());
        
    }
    
    // Obtener un producto por su id
    @GetMapping("/api/products/{id}")
    public ResponseEntity<ProductoDTO> findById(@PathVariable Long id) {
        
        String baseUrl = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .build()
                .toUriString();
        
        return repository.findById(id)
            .map(producto -> {
                ProductoDTO dto = new ProductoDTO(producto);

                if (dto.getImagen() != null && dto.getImagen().startsWith("/")) {
                    dto.setImagen(baseUrl + dto.getImagen());
                }

                return ResponseEntity.ok(dto);
            })
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    //Crear un instrumento
    @PostMapping("/api/products")
    public ResponseEntity<Producto> create(@RequestBody Producto instrumento) {
        if (instrumento.getId() != null) {
            return ResponseEntity.badRequest().build();
        } else {
            Producto instrumentoSaved = repository.save(instrumento);
            return ResponseEntity.ok(instrumentoSaved);
        }
    }

    //Modifico un instrumento
    @PutMapping("/api/products/{id}")
    public ResponseEntity<Producto> update(@PathVariable Long id, @RequestBody Producto producto) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        } else {
            producto.setId(id);
            Producto instrumentoSaved = repository.save(producto);
            return ResponseEntity.ok(instrumentoSaved);
        }
    }

    //Eliminar un instrumento
    @DeleteMapping("/api/products/{id}")
    public ResponseEntity<Producto> delete(@PathVariable Long id) {
        //Comprueba que no existe para devolver el error notFound
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        } else {
            repository.deleteById(id);
            //El build genera el objeto
            return ResponseEntity.noContent().build();
        }
    }

    //Buscamos un instrumento por subcategoria
    @GetMapping("/api/products/subcategory/{id}")
    public ResponseEntity<List<ProductoDTO>> findBySubCategoria(@PathVariable Long id) {

        String baseUrl = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .build()
                .toUriString();

        List<ProductoDTO> productos = repository.findBySubCategoriaId(id)
                .stream()
                .map(ProductoDTO::new)
                .peek(dto -> {
                    if (dto.getImagen() != null && dto.getImagen().startsWith("/")) {
                        dto.setImagen(baseUrl + dto.getImagen());
                    }
                })
                .toList();

        return ResponseEntity.ok(productos); // 200 aunque esté vacío
    }

    // filtro para mostrar únicamente productos disponibles
    @GetMapping("/api/products/stock")
    public ResponseEntity<List<Producto>> findWithStock() {

        List<Producto> instrumentos = repository.findByStockGreaterThan(0);

        if (instrumentos.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(instrumentos);
    }

    @GetMapping("/api/products/precio")
    public ResponseEntity<List<Producto>> findByPrecio(
            @RequestParam double min,
            @RequestParam double max) {

        if (min < 0 || max < 0 || min > max) {
            return ResponseEntity.badRequest().build();
        }
        //System.out.println("min: " + min + ", max: " + max);

        List<Producto> instrumentos = repository.findByPrecioBetween(min, max);

        if (instrumentos.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(instrumentos);
    }

}
