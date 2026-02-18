/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.api.musiclab.controller;

import com.api.musiclab.dto.SubCategoriaDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.api.musiclab.dto.SubCategoriaRequest;
import com.api.musiclab.entities.Categoria;
import com.api.musiclab.entities.SubCategoria;
import com.api.musiclab.repository.CategoriaRepository;
import com.api.musiclab.repository.SubCategoriaRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 *
 * @author danig
 */
@RestController
@RequestMapping("/api")
public class SubCategoriaController {
    
    private static final Logger log = LoggerFactory.getLogger(SubCategoriaController.class);
    
    private static final Path UPLOAD_DIR =
        Paths.get("uploads", "subcategories").toAbsolutePath().normalize();
    
    private final SubCategoriaRepository subCategoriaRepository;
    private final CategoriaRepository categoriaRepository;
    
    public SubCategoriaController(SubCategoriaRepository subCategoriaRepository,
                                CategoriaRepository categoriaRepository) {
        this.subCategoriaRepository = subCategoriaRepository;
        this.categoriaRepository = categoriaRepository;
    }
    
    @GetMapping("/subcategories")
    public ResponseEntity<List<SubCategoriaDTO>> listar(
            @RequestParam(name = "q", required = false) String q,
            @RequestParam(name = "cId", required = false) Long cId,
            @PageableDefault(size = 10) Pageable pageable
    ) {
        String baseUrl = ServletUriComponentsBuilder
            .fromCurrentContextPath()
            .build()
            .toUriString();
        
         Page<SubCategoria> page = 
            (cId != null && q != null && !q.isBlank())
                ? subCategoriaRepository.findByCategoriaIdAndNombreContainingIgnoreCase(cId, q.trim(), pageable)
            : (cId != null)
                ? subCategoriaRepository.findByCategoriaId(cId, pageable)
            : (q != null && !q.isBlank())
                ? subCategoriaRepository.findByNombreContainingIgnoreCase(q.trim(), pageable)
            : subCategoriaRepository.findAll(pageable);
         
         List<SubCategoriaDTO> dtoList = page.getContent().stream()
            .map(sc -> {
                SubCategoriaDTO dto = new SubCategoriaDTO(sc);
                if (dto.getImagenUrl() != null && dto.getImagenUrl().startsWith("/")) {
                    dto.setImagenUrl(baseUrl + dto.getImagenUrl());
                }
                return dto;
            })
            .toList();

        return ResponseEntity.ok()
                .header("X-Total-Count", String.valueOf(page.getTotalElements()))
                .header("X-Total-Pages", String.valueOf(page.getTotalPages()))
                .body(dtoList);
                
    }
    
    @GetMapping("/subcategories/{id}")
    public ResponseEntity<SubCategoriaDTO> obtener(@PathVariable Long id) {

        String baseUrl = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .build()
                .toUriString();

        return subCategoriaRepository.findById(id)
                .map(sc -> {
                    SubCategoriaDTO dto = new SubCategoriaDTO(sc);

                    // Si la imagen viene como "/uploads/..." la convertimos a absoluta
                    if (dto.getImagenUrl() != null && dto.getImagenUrl().startsWith("/")) {
                        dto.setImagenUrl(baseUrl + dto.getImagenUrl());
                    }

                    return ResponseEntity.ok(dto);
                })
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/categories/{categoryId}/subcategories")
    public ResponseEntity<List<SubCategoriaDTO>> listarPorCategoria(@PathVariable Long categoryId) {

        if (!categoriaRepository.existsById(categoryId)) {
            return ResponseEntity.notFound().build();
        }

        String baseUrl = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .build()
                .toUriString();

        List<SubCategoriaDTO> dtoList = subCategoriaRepository.findByCategoriaId(categoryId)
                .stream()
                .map(sc -> {
                    SubCategoriaDTO dto = new SubCategoriaDTO(sc);
                    if (dto.getImagenUrl() != null && dto.getImagenUrl().startsWith("/")) {
                        dto.setImagenUrl(baseUrl + dto.getImagenUrl());
                    }
                    return dto;
                })
                .toList();

        return ResponseEntity.ok(dtoList);
    }

    
    @PostMapping("/subcategories")
    public ResponseEntity<?> crear(@Valid @RequestBody SubCategoriaRequest req) {
        Categoria categoria = categoriaRepository.findById(req.getCategoriaId()).orElse(null);
        if (categoria == null) return ResponseEntity.notFound().build();

        SubCategoria sc = new SubCategoria();
        sc.setNombre(req.getNombre().trim());
        sc.setCategoria(categoria);
        sc.setImagenUrl(null);

        return ResponseEntity.ok(subCategoriaRepository.save(sc));
    }
    
    @PutMapping(value = "/subcategories/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadImage(@PathVariable Long id,
                                         @RequestParam("file") MultipartFile file) throws Exception {

        SubCategoria sc = subCategoriaRepository.findById(id).orElse(null);
        if (sc == null) return ResponseEntity.notFound().build();

        if (file == null || file.isEmpty()) return ResponseEntity.badRequest().body("Archivo vacío");
        if (file.getContentType() == null || !file.getContentType().startsWith("image/")) {
            return ResponseEntity.badRequest().body("El archivo debe ser una imagen");
        }

        Files.createDirectories(UPLOAD_DIR);

        // ✅ borrar imagen anterior
        borrarImagenSiExiste(sc.getImagenUrl());

        String ext = Optional.ofNullable(file.getOriginalFilename())
                .filter(n -> n.contains("."))
                .map(n -> n.substring(n.lastIndexOf(".")))
                .orElse("");

        String filename = "subcat-" + id + "-" + UUID.randomUUID() + ext;
        Path path = UPLOAD_DIR.resolve(filename).normalize();

        Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

        sc.setImagenUrl("/images/subcategories/" + filename);
        return ResponseEntity.ok(subCategoriaRepository.save(sc));
    }
    
    @PutMapping("/subcategories/{id}")
    public ResponseEntity<SubCategoria> actualizar(@PathVariable Long id,
                                                @Valid @RequestBody SubCategoriaRequest req) {

        SubCategoria existente = subCategoriaRepository.findById(id).orElse(null);
        if (existente == null) return ResponseEntity.notFound().build();

        Categoria categoria = categoriaRepository.findById(req.getCategoriaId()).orElse(null);
        if (categoria == null) return ResponseEntity.notFound().build();

        existente.setNombre(req.getNombre().trim());
        existente.setCategoria(categoria);

        // ❌ NO tocar imagenUrl aquí
        return ResponseEntity.ok(subCategoriaRepository.save(existente));
    }
    
    @DeleteMapping("/subcategories/{id}")
    @Transactional
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        var sc = subCategoriaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        String imageUrl = sc.getImagenUrl();

        subCategoriaRepository.delete(sc);

        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
            @Override
            public void afterCommit() {
                borrarImagenSiExiste(imageUrl);
            }
        });

        return ResponseEntity.noContent().build();
    }

    private void borrarImagenSiExiste(String imagenUrl) {
        if (imagenUrl == null || imagenUrl.isBlank()) return;

        try {
            // imagenUrl puede ser "/images/subcategories/xxx.png" o URL completa
            String filename = imagenUrl.substring(imagenUrl.lastIndexOf('/') + 1);
            // por si viene con query params ?v=...
            int q = filename.indexOf('?');
            if (q >= 0) filename = filename.substring(0, q);

            Path filePath = UPLOAD_DIR.resolve(filename).normalize();

            if (!filePath.startsWith(UPLOAD_DIR)) {
                System.err.println("Ruta sospechosa, no borro: " + filePath);
                return;
            }

            boolean deleted = Files.deleteIfExists(filePath);
            System.out.println("Borrando imagen: " + filePath + " deleted=" + deleted);

        } catch (Exception e) {
            System.err.println("No se pudo borrar imagen: " + imagenUrl + " -> " + e.getMessage());
        }
    }
    
}
