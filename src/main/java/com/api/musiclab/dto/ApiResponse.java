/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.api.musiclab.dto;

/**
 *
 * @author danig
 */
public class ApiResponse {
    
    private String message;
    
    public ApiResponse(String message) {
        this.message = message;
    }
    
    public String getMessage() {
        return message;
    }
    
}
