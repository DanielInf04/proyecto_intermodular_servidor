/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.api.musiclab.config;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *
 * @author danig
 */
@Configuration
public class StaticResourceConfig implements WebMvcConfigurer {

   @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/uploads/**")
            .addResourceLocations("classpath:/static/uploads/");
        
        /*Path uploadsDir = Paths.get("uploads").toAbsolutePath().normalize();
        String uploadsUri = uploadsDir.toUri().toString();
        if (!uploadsUri.endsWith("/")) uploadsUri += "/";

        // 🔍 DEBUG: ruta real y existencia del archivo que estás probando
        Path testFile = uploadsDir.resolve("subcategories").resolve("guitarra_electrica.jpg");
        System.out.println("✅ StaticResourceConfig cargado");
        System.out.println("📁 Sirviendo /images/** desde: " + uploadsUri);
        System.out.println("🧪 Probando fichero: " + testFile);
        System.out.println("🧪 Existe? " + Files.exists(testFile));

        registry.addResourceHandler("/images/**")
                .addResourceLocations(uploadsUri);*/
    }
}