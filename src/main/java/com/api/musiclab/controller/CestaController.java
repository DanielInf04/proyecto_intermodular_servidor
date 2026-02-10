/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.api.musiclab.controller;

import com.api.musiclab.entities.Cesta;
import java.time.LocalDate;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.api.musiclab.repository.CestaRepository;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author danig
 */
@RestController
public class CestaController {
    private final CestaRepository cestaRepository;

    public CestaController(CestaRepository pedidoRepository) {
        this.cestaRepository = pedidoRepository;
    }

    /*@GetMapping("/usuario/{userId}")
    public ResponseEntity<Cesta> findByUserId(@PathVariable Long userId) {
        return cestaRepository.findByUserId(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping("/usuario/{userId}/lineas")
    public ResponseEntity<Cesta> addProducto(@PathVariable Long userId,
                                             @RequestBody AddToCestaRequest req) {
        Cesta cesta = cestaRepository.addProducto(userId, req.getProductoId(), req.getCantidad());
        return ResponseEntity.ok(cesta);
    }*/
    
    
    
}
