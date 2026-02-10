/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.api.musiclab.controller;

import com.api.musiclab.entities.Producto;
import com.api.musiclab.entities.LineaCesta;
import com.api.musiclab.entities.Cesta;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.api.musiclab.repository.CestaRepository;
import com.api.musiclab.repository.LineaCestaRepository;
import com.api.musiclab.repository.ProductoRepository;

/**
 *
 * @author davidlema
 */
@RestController
public class LineaCestaController {
    private final LineaCestaRepository lineaPedidoRepository;
    private final CestaRepository pedidoRepository;
    private final ProductoRepository instrumentoRepository;

    public LineaCestaController(
            LineaCestaRepository lineaPedidoRepository,
            CestaRepository pedidoRepository,
            ProductoRepository instrumentoRepository) {

        this.lineaPedidoRepository = lineaPedidoRepository;
        this.pedidoRepository = pedidoRepository;
        this.instrumentoRepository = instrumentoRepository;
    }

    /*@PostMapping("/api/pedidos/{pedidoId}/lineas")
    public ResponseEntity<?> addLineaPedido(
            @PathVariable Long pedidoId,
            @RequestParam Long instrumentoId,
            @RequestParam int cantidad) {

        Cesta pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));

        Producto instrumento = instrumentoRepository.findById(instrumentoId)
                .orElseThrow(() -> new RuntimeException("Instrumento no encontrado"));

        LineaCesta linea = new LineaCesta();
        linea.setPedido(pedido);
        linea.setInstrumento(instrumento);
        linea.setCantidad(cantidad);
        linea.setPrecioUnitario(instrumento.getPrecio());

        return ResponseEntity.ok(lineaPedidoRepository.save(linea));
    }*/
}
