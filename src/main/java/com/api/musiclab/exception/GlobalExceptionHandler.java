/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.api.musiclab.exception;

import com.api.musiclab.dto.ApiResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 *
 * @author danig
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> handleValidation(MethodArgumentNotValidException ex) {
        FieldError fe = ex.getBindingResult().getFieldErrors().stream()
                .findFirst().orElse(null);

        String msg = (fe != null && fe.getDefaultMessage() != null)
                ? fe.getDefaultMessage()
                : "Datos inválidos";

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiResponse(msg));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiResponse> handleIntegrity(DataIntegrityViolationException ex) {

        String detail = ex.getMostSpecificCause() != null
                ? ex.getMostSpecificCause().getMessage()
                : (ex.getMessage() != null ? ex.getMessage() : "");

        String d = detail.toLowerCase();

        if (d.contains("email") && (d.contains("duplicate") || d.contains("unique") || d.contains("constraint"))) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ApiResponse("El correo ya está registrado"));
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse("No se pudo completar el registro. Revisa los datos e inténtalo de nuevo."));
    }
    
}
