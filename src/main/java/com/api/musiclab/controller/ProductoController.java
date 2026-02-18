/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.api.musiclab.controller;

import org.springframework.web.server.ResponseStatusException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import com.api.musiclab.dto.ProductoDTO;
import com.api.musiclab.dto.ProductoRequest;
import com.api.musiclab.entities.Producto;
import com.api.musiclab.entities.SubCategoria;
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
import com.api.musiclab.repository.SubCategoriaRepository;
import jakarta.validation.Valid;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 *
 * @author danig
 */

@RestController
@RequestMapping("/api")
public class ProductoController {

    private static final Path UPLOAD_DIR =
        Paths.get("uploads", "products").toAbsolutePath().normalize();
    
    private final ProductoRepository repository;
    private final SubCategoriaRepository subCategoriaRepository;

    public ProductoController(ProductoRepository repository, SubCategoriaRepository subCategoriaRepository) {
        this.repository = repository;
        this.subCategoriaRepository = subCategoriaRepository;
    }
    
    @GetMapping("/products")
    public ResponseEntity<List<ProductoDTO>> listar(
            @RequestParam(required = false) String q,
            @RequestParam(name = "cId", required = false) Long cId,
            @RequestParam(name = "scId", required = false) Long scId,
            @RequestParam(name = "stock", required = false) String stock, // ✅
            @PageableDefault(size = 10) Pageable pageable
    ) {
        String baseUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
        String qq = (q == null || q.isBlank()) ? null : q.trim();

        // low = stock <= 5 (puedes cambiarlo)
        Integer minStock = null;
        Integer maxStock = null;

        if ("in".equalsIgnoreCase(stock)) minStock = 1;
        if ("out".equalsIgnoreCase(stock)) maxStock = 0;
        if ("low".equalsIgnoreCase(stock)) { minStock = 1; maxStock = 5; }

        Page<Producto> page = repository.search(qq, cId, scId, minStock, maxStock, pageable);

        List<ProductoDTO> dtoList = page.getContent().stream().map(p -> {
            ProductoDTO dto = new ProductoDTO(p);
            if (dto.getImagen() != null && dto.getImagen().startsWith("/")) dto.setImagen(baseUrl + dto.getImagen());
            return dto;
        }).toList();

        return ResponseEntity.ok()
                .header("X-Total-Count", String.valueOf(page.getTotalElements()))
                .header("X-Total-Pages", String.valueOf(page.getTotalPages()))
                .body(dtoList);
    }
    
    /*@GetMapping("/products")
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
        
    }*/
    
    // Obtener un producto por su id
    @GetMapping("/products/{id}")
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
    
    @PostMapping("/products")
    public ResponseEntity<?> create(@Valid @RequestBody ProductoRequest req) {
        
        Producto p = new Producto();
        p.setNombre(req.getNombre().trim());
        p.setMarca(req.getMarca().trim());
        p.setPrecio(req.getPrecio());
        p.setStock(req.getStock());
        p.setDescripcion(req.getDescripcion() == null ? "" : req.getDescripcion().trim());
        
        SubCategoria sc = subCategoriaRepository.findById(req.getSubcategoriaId()).orElse(null);
        if (sc == null) return ResponseEntity.notFound().build();
        
        p.setSubCategoria(sc);
        
        p.setImagen(null);
        
        return ResponseEntity.ok(repository.save(p));
    }

    //Crear un instrumento
    /*@PostMapping("/api/products")
    public ResponseEntity<Producto> create(@RequestBody Producto instrumento) {
        if (instrumento.getId() != null) {
            return ResponseEntity.badRequest().build();
        } else {
            Producto instrumentoSaved = repository.save(instrumento);
            return ResponseEntity.ok(instrumentoSaved);
        }
    }*/
    
    // Actualizar un producto
    @PutMapping("/products/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody ProductoRequest req) {

        Producto existente = repository.findById(id).orElse(null);
        if (existente == null) return ResponseEntity.notFound().build();

        existente.setNombre(req.getNombre().trim());
        existente.setMarca(req.getMarca().trim());
        existente.setPrecio(req.getPrecio());
        existente.setStock(req.getStock());
        existente.setDescripcion(req.getDescripcion() == null ? "" : req.getDescripcion().trim());
        
        SubCategoria sc = subCategoriaRepository.findById(req.getSubcategoriaId()).orElse(null);
        if (sc == null) return ResponseEntity.notFound().build();

        existente.setSubCategoria(sc);

        // actualizar subcategoría igual que en create (sin tocar imagen)
        return ResponseEntity.ok(repository.save(existente));
    }
    
    @PutMapping(value = "/products/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadImage(@PathVariable Long id,
                                         @RequestParam("file") MultipartFile file) throws Exception {

        Producto p = repository.findById(id).orElse(null);
        if (p == null) return ResponseEntity.notFound().build();

        if (file == null || file.isEmpty()) return ResponseEntity.badRequest().body("Archivo vacío");
        if (file.getContentType() == null || !file.getContentType().startsWith("image/")) {
            return ResponseEntity.badRequest().body("El archivo debe ser una imagen");
        }

        Files.createDirectories(UPLOAD_DIR);

        // borrar anterior
        borrarImagenSiExiste(p.getImagen());

        String ext = Optional.ofNullable(file.getOriginalFilename())
                .filter(n -> n.contains("."))
                .map(n -> n.substring(n.lastIndexOf(".")))
                .orElse("");

        String filename = "product-" + id + "-" + UUID.randomUUID() + ext;
        Path path = UPLOAD_DIR.resolve(filename).normalize();

        Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

        p.setImagen("/images/products/" + filename);
        return ResponseEntity.ok(repository.save(p));
    }

    //Modifico un instrumento
    /*@PutMapping("/api/products/{id}")
    public ResponseEntity<Producto> update(@PathVariable Long id, @RequestBody Producto producto) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        } else {
            producto.setId(id);
            Producto instrumentoSaved = repository.save(producto);
            return ResponseEntity.ok(instrumentoSaved);
        }
    }*/
    
    private void borrarImagenSiExiste(String imagenUrl) {
        if (imagenUrl == null || imagenUrl.isBlank()) return;

        try {
            String filename = imagenUrl.substring(imagenUrl.lastIndexOf('/') + 1);
            int q = filename.indexOf('?');
            if (q >= 0) filename = filename.substring(0, q);

            Path filePath = UPLOAD_DIR.resolve(filename).normalize();

            if (!filePath.startsWith(UPLOAD_DIR)) return;

            Files.deleteIfExists(filePath);
        } catch (Exception e) {
            System.err.println("No se pudo borrar imagen: " + imagenUrl + " -> " + e.getMessage());
        }
    }
    
    // Eliminar un producto
    @DeleteMapping("/products/{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        var p = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        String imageUrl = p.getImagen(); // guarda antes

        repository.delete(p);

        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
            @Override
            public void afterCommit() {
                borrarImagenSiExiste(imageUrl);
            }
        });

        return ResponseEntity.noContent().build();
    }

    //Eliminar un instrumento
    /*@DeleteMapping("/api/products/{id}")
    public ResponseEntity<Producto> delete(@PathVariable Long id) {
        //Comprueba que no existe para devolver el error notFound
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        } else {
            repository.deleteById(id);
            //El build genera el objeto
            return ResponseEntity.noContent().build();
        }
    }*/

    //Buscamos un instrumento por subcategoria
    @GetMapping("/products/subcategory/{id}")
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
    @GetMapping("/products/stock")
    public ResponseEntity<List<Producto>> findWithStock() {

        List<Producto> instrumentos = repository.findByStockGreaterThan(0);

        if (instrumentos.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(instrumentos);
    }

    @GetMapping("/products/precio")
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
