/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.api.musiclab.config;

import com.api.musiclab.entities.Categoria;
import com.api.musiclab.entities.Producto;
import com.api.musiclab.entities.SubCategoria;
import com.api.musiclab.entities.Usuario;
import com.api.musiclab.repository.CategoriaRepository;
import com.api.musiclab.repository.ProductoRepository;
import com.api.musiclab.repository.SubCategoriaRepository;
import com.api.musiclab.repository.UsuarioRepository;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 *
 * @author danig
 */

@Component
@Profile("dev")
public class DataSeeder implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final CategoriaRepository categoriaRepository;
    private final SubCategoriaRepository subCategoriaRepository;
    private final ProductoRepository productoRepository;
    private final PasswordEncoder encoder;
    
    public DataSeeder(
            UsuarioRepository usuarioRepository,
            CategoriaRepository categoriaRepository,
            SubCategoriaRepository subCategoriaRepository,
            ProductoRepository productoRepository,
            PasswordEncoder encoder
    ) {
        this.usuarioRepository = usuarioRepository;
        this.categoriaRepository = categoriaRepository;
        this.subCategoriaRepository = subCategoriaRepository;
        this.productoRepository = productoRepository;
        this.encoder = encoder;
    }
    
    @Override
    public void run(String... args) throws Exception {

        // Evita duplicar si ya hay datos
        if (productoRepository.count() > 0 || categoriaRepository.count() > 0) {
            System.out.println("[Seeder] Ya hay datos, no se carga seed.");
            return;
        }

        // 1) Categorías (por ID del CSV)
        Map<Long, Categoria> categoriasById = loadCategorias("seed/categorias.csv");

        // 2) Subcategorías (por ID del CSV)
        Map<Long, SubCategoria> subcatsById = loadSubcategorias("seed/subcategorias.csv", categoriasById);

        // 3) Productos (por ID del CSV)
        loadProductos("seed/productos.csv", subcatsById);

        // 4) Usuarios
        loadUsuarios("seed/usuarios.csv");

        System.out.println("[Seeder] Categorias: " + categoriaRepository.count());
        System.out.println("[Seeder] Subcategorias: " + subCategoriaRepository.count());
        System.out.println("[Seeder] Productos: " + productoRepository.count());
        System.out.println("[Seeder] Usuarios: " + usuarioRepository.count());
    }
    
    private Map<Long, Categoria> loadCategorias(String path) throws IOException {
        Map<Long, Categoria> map = new HashMap<>();

        try (BufferedReader br = reader(path)) {
            br.readLine(); // saltar header

            String line;
            while ((line = br.readLine()) != null) {
                if (line.isBlank()) continue;

                String[] c = splitCsv(line);

                Long id = Long.parseLong(clean(c[0]));
                String nombre = clean(c[1]);

                Categoria cat = new Categoria();
                cat.setNombre(nombre);

                categoriaRepository.save(cat);

                map.put(id, cat);
            }
        }
        return map;
    }
    
    private Map<Long, SubCategoria> loadSubcategorias(
        String path,
        Map<Long, Categoria> categoriasById) throws IOException {

        Map<Long, SubCategoria> map = new HashMap<>();

        try (BufferedReader br = reader(path)) {
            br.readLine(); // header

            String line;
            while ((line = br.readLine()) != null) {
                if (line.isBlank()) continue;

                String[] c = splitCsv(line);

                Long categoriaId = Long.parseLong(clean(c[0]));
                Long id = Long.parseLong(clean(c[1]));
                String nombre = clean(c[2]);
                String imagenUrl = clean(c[3]);

                Categoria cat = categoriasById.get(categoriaId);
                if (cat == null) {
                    throw new IllegalStateException("No existe categoria id " + categoriaId);
                }

                SubCategoria sc = new SubCategoria();
                sc.setNombre(nombre);
                sc.setImagenUrl(imagenUrl);
                sc.setCategoria(cat);

                subCategoriaRepository.save(sc);
                map.put(id, sc);
            }
        }

        return map;
    }

    private void loadProductos(
        String path,
        Map<Long, SubCategoria> subcatsById) throws IOException {

        try (BufferedReader br = reader(path)) {
            br.readLine(); // header

            String line;
            while ((line = br.readLine()) != null) {
                if (line.isBlank()) continue;

                String[] c = splitCsv(line);

                double precio = Double.parseDouble(clean(c[0]));
                int stock = Integer.parseInt(clean(c[1]));
                Long id = Long.parseLong(clean(c[2]));
                Long subcategoriaId = Long.parseLong(clean(c[3]));
                String descripcion = clean(c[4]);
                String imagen = clean(c[5]);
                String marca = clean(c[6]);
                String nombre = clean(c[7]);

                SubCategoria sc = subcatsById.get(subcategoriaId);
                if (sc == null) {
                    throw new IllegalStateException("No existe subcategoria id " + subcategoriaId);
                }

                Producto p = new Producto();
                p.setNombre(nombre);
                p.setMarca(marca);
                p.setStock(stock);
                p.setPrecio(precio);
                p.setDescripcion(descripcion);
                p.setImagen(imagen);
                p.setSubCategoria(sc);

                productoRepository.save(p);
            }
        }
    }

    private void loadUsuarios(String path) throws IOException {
        try (BufferedReader br = reader(path)) {
            br.readLine(); // header

            String line;
            while ((line = br.readLine()) != null) {
                if (line.isBlank()) continue;

                String[] c = splitCsv(line);

                String fechaAlta = clean(c[0]);
                String role = clean(c[3]);
                String username = clean(c[4]);
                String email = clean(c[6]);
                String password = clean(c[7]);

                Usuario u = new Usuario();
                u.setUsername(username);
                u.setEmail(email);
                u.setPassword(password);
                u.setRole(role);
                u.setFechaAlta(LocalDate.parse(fechaAlta));

                usuarioRepository.save(u);
            }
        }
    }
    private BufferedReader reader(String classpathLocation) throws IOException {
        InputStream is = new ClassPathResource(classpathLocation).getInputStream();
        return new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
    }
    
    private String clean(String s) {
        if (s == null) return "";
        s = s.trim();

        // quita BOM si aparece al principio del primer campo
        if (!s.isEmpty() && s.charAt(0) == '\uFEFF') {
            s = s.substring(1);
        }

        // quita comillas "..." y también comillas sueltas dentro
        if (s.startsWith("\"") && s.endsWith("\"") && s.length() >= 2) {
            s = s.substring(1, s.length() - 1);
        }
        s = s.replace("\"", "").trim(); // por si vienen ""1""

        return s;
    }
    
    /**
     * Split CSV muy simple: funciona si tus campos NO llevan comas dentro.
     * Si tienes descripciones con comas, te recomiendo usar OpenCSV.
     */
    private String[] splitCsv(String line) {
        return line.split(",", -1);
    }
    
}
