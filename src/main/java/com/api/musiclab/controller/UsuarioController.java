/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.api.musiclab.controller;

import com.api.musiclab.dto.UsuarioDTO;
import com.api.musiclab.entities.Usuario;
import com.api.musiclab.repository.UsuarioRepository;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author 7N
 */
@RestController
public class UsuarioController {

    private final UsuarioRepository repository;

    public UsuarioController(UsuarioRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/api/users")
    public ResponseEntity<List<UsuarioDTO>> listar(
            @RequestParam(required = false) String q,
            @PageableDefault(size = 10) Pageable pageable
    ) {

        String qq = (q == null || q.isBlank()) ? null : q.trim();

        Page<Usuario> page;

        if (qq != null) {
            page = repository
                    .findByUsernameContainingIgnoreCaseOrEmailContainingIgnoreCase(
                            qq, qq, pageable
                    );
        } else {
            page = repository.findAll(pageable);
        }

        List<UsuarioDTO> dtoList = page.getContent()
                .stream()
                .map(u -> new UsuarioDTO(
                u.getId(),
                u.getUsername(),
                u.getEmail(),
                u.getRole(), // 👈 IMPORTANTE
                u.getFechaAlta()
        ))
                .toList();

        return ResponseEntity.ok()
                .header("X-Total-Count", String.valueOf(page.getTotalElements()))
                .header("X-Total-Pages", String.valueOf(page.getTotalPages()))
                .body(dtoList);
    }

    @PostMapping("/api/users")
    public ResponseEntity<Usuario> create(@RequestBody Usuario usuario) {
        Usuario saved = repository.save(usuario);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/api/users/{id}")
    public ResponseEntity<Usuario> update(
            @PathVariable Long id,
            @RequestBody Usuario data
    ) {
        Usuario u = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        u.setUsername(data.getUsername());
        u.setEmail(data.getEmail());

        return ResponseEntity.ok(repository.save(u));
    }

    @DeleteMapping("/api/users/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
