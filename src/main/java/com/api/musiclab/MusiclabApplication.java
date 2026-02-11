package com.api.musiclab;

import com.api.musiclab.entities.*;
import com.api.musiclab.repository.*;
import java.time.LocalDate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class MusiclabApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(MusiclabApplication.class, args);
        System.out.println("API iniciada correctamente de MusicLab");
        System.out.println("WORKDIR = " + java.nio.file.Paths.get("").toAbsolutePath());

        // ======================
        // REPOSITORIES
        // ======================
        UsuarioRepository usuarioRepository = context.getBean(UsuarioRepository.class);
        CategoriaRepository categoriaRepository = context.getBean(CategoriaRepository.class);
        SubCategoriaRepository subCategoriaRepository = context.getBean(SubCategoriaRepository.class);
        ProductoRepository productoRepository = context.getBean(ProductoRepository.class);
        CestaRepository cestaRepository = context.getBean(CestaRepository.class);
        LineaCestaRepository lineaCestaRepository = context.getBean(LineaCestaRepository.class);
        PasswordEncoder encoder = context.getBean(PasswordEncoder.class);

        // ======================
        // USUARIOS
        // ======================
        Usuario u1 = new Usuario();
        u1.setUsername("carlos");
        u1.setEmail("carlos@musiclab.com");
        u1.setPassword(encoder.encode("1234"));
        u1.setRole("USER");
        u1.setFechaAlta(LocalDate.now());
        usuarioRepository.save(u1);

        Usuario u2 = new Usuario();
        u2.setUsername("laura");
        u2.setEmail("laura@musiclab.com");
        u2.setPassword(encoder.encode("1234"));
        u2.setRole("ADMIN");
        u2.setFechaAlta(LocalDate.now());
        usuarioRepository.save(u2);
        
        // ======================
        // CATEGORÍAS
        // ======================
        Categoria catCuerdas = new Categoria();
        catCuerdas.setNombre("Cuerdas");
        categoriaRepository.save(catCuerdas);

        Categoria catTeclas = new Categoria();
        catTeclas.setNombre("Teclas");
        categoriaRepository.save(catTeclas);

        Categoria catPercusion = new Categoria();
        catPercusion.setNombre("Percusión");
        categoriaRepository.save(catPercusion);

        Categoria catVientoMadera = new Categoria();
        catVientoMadera.setNombre("Viento madera");
        categoriaRepository.save(catVientoMadera);

        Categoria catVientoMetal = new Categoria();
        catVientoMetal.setNombre("Viento metal");
        categoriaRepository.save(catVientoMetal);

        Categoria catTradicionales = new Categoria();
        catTradicionales.setNombre("Tradicionales");
        categoriaRepository.save(catTradicionales);

        // ======================
        // SUBCATEGORÍAS
        // ======================
        // ---- CUERDAS
        SubCategoria subGuitElec = new SubCategoria();
        subGuitElec.setNombre("Guitarras eléctricas");
        subGuitElec.setImagenUrl("/images/subcategories/guitarra_electrica.jpg");
        subGuitElec.setCategoria(catCuerdas);
        subCategoriaRepository.save(subGuitElec);

        SubCategoria subGuitAcust = new SubCategoria();
        subGuitAcust.setNombre("Guitarras acústicas");
        subGuitAcust.setImagenUrl("/images/subcategories/guitarra_acustica.jpg");
        subGuitAcust.setCategoria(catCuerdas);
        subCategoriaRepository.save(subGuitAcust);

        SubCategoria subBajos = new SubCategoria();
        subBajos.setNombre("Bajos");
        subBajos.setImagenUrl("/images/subcategories/bajo.jpg");
        subBajos.setCategoria(catCuerdas);
        subCategoriaRepository.save(subBajos);

        SubCategoria subUkeleles = new SubCategoria();
        subUkeleles.setNombre("Ukeleles");
        subUkeleles.setImagenUrl("/images/subcategories/ukelele.jpg");
        subUkeleles.setCategoria(catCuerdas);
        subCategoriaRepository.save(subUkeleles);

        SubCategoria subViolines = new SubCategoria();
        subViolines.setNombre("Violines");
        subViolines.setImagenUrl("/images/subcategories/violin.jpg");
        subViolines.setCategoria(catCuerdas);
        subCategoriaRepository.save(subViolines);

        // ---- TECLAS
        SubCategoria subPianos = new SubCategoria();
        subPianos.setNombre("Pianos digitales");
        subPianos.setImagenUrl("/images/subcategories/piano_digital.jpg");
        subPianos.setCategoria(catTeclas);
        subCategoriaRepository.save(subPianos);

        SubCategoria subArranger = new SubCategoria();
        subArranger.setNombre("Teclados arranger");
        subArranger.setImagenUrl("/images/subcategories/teclado_arranger.jpg");
        subArranger.setCategoria(catTeclas);
        subCategoriaRepository.save(subArranger);

        SubCategoria subSintetizadores = new SubCategoria();
        subSintetizadores.setNombre("Sintetizadores");
        subSintetizadores.setImagenUrl("/images/subcategories/sintetizador.jpg");
        subSintetizadores.setCategoria(catTeclas);
        subCategoriaRepository.save(subSintetizadores);

        SubCategoria subOrganos = new SubCategoria();
        subOrganos.setNombre("Órganos");
        subOrganos.setImagenUrl("/images/subcategories/organo.jpg");
        subOrganos.setCategoria(catTeclas);
        subCategoriaRepository.save(subOrganos);

        // ---- PERCUSIÓN
        SubCategoria subBateriasAcust = new SubCategoria();
        subBateriasAcust.setNombre("Baterías acústicas");
        subBateriasAcust.setImagenUrl("/images/subcategories/bateria_acustica.jpg");
        subBateriasAcust.setCategoria(catPercusion);
        subCategoriaRepository.save(subBateriasAcust);

        SubCategoria subBateriasElec = new SubCategoria();
        subBateriasElec.setNombre("Baterías electrónicas");
        subBateriasElec.setImagenUrl("/images/subcategories/bateria_electronica.jpg");
        subBateriasElec.setCategoria(catPercusion);
        subCategoriaRepository.save(subBateriasElec);

        SubCategoria subCajones = new SubCategoria();
        subCajones.setNombre("Cajones");
        subCajones.setImagenUrl("/images/subcategories/cajon.jpg");
        subCajones.setCategoria(catPercusion);
        subCategoriaRepository.save(subCajones);

        SubCategoria subCongas = new SubCategoria();
        subCongas.setNombre("Congas y bongos");
        subCongas.setImagenUrl("/images/subcategories/congas_bongos.jpg");
        subCongas.setCategoria(catPercusion);
        subCategoriaRepository.save(subCongas);

        SubCategoria subPlatos = new SubCategoria();
        subPlatos.setNombre("Platos");
        subPlatos.setImagenUrl("/images/subcategories/platos.jpg");
        subPlatos.setCategoria(catPercusion);
        subCategoriaRepository.save(subPlatos);

        // ---- VIENTO MADERA
        SubCategoria subSaxos = new SubCategoria();
        subSaxos.setNombre("Saxofones");
        subSaxos.setImagenUrl("/images/subcategories/saxofon.jpg");
        subSaxos.setCategoria(catVientoMadera);
        subCategoriaRepository.save(subSaxos);

        SubCategoria subClarinetes = new SubCategoria();
        subClarinetes.setNombre("Clarinetes");
        subClarinetes.setImagenUrl("/images/subcategories/clarinete.jpg");
        subClarinetes.setCategoria(catVientoMadera);
        subCategoriaRepository.save(subClarinetes);

        SubCategoria subFlautas = new SubCategoria();
        subFlautas.setNombre("Flautas traveseras");
        subFlautas.setImagenUrl("/images/subcategories/flauta_travesera.jpg");
        subFlautas.setCategoria(catVientoMadera);
        subCategoriaRepository.save(subFlautas);

        SubCategoria subOboes = new SubCategoria();
        subOboes.setNombre("Oboes");
        subOboes.setImagenUrl("/images/subcategories/oboe.jpg");
        subOboes.setCategoria(catVientoMadera);
        subCategoriaRepository.save(subOboes);

        // ---- VIENTO METAL
        SubCategoria subTrompetas = new SubCategoria();
        subTrompetas.setNombre("Trompetas");
        subTrompetas.setImagenUrl("/images/subcategories/trompeta.jpg");
        subTrompetas.setCategoria(catVientoMetal);
        subCategoriaRepository.save(subTrompetas);

        SubCategoria subTrombones = new SubCategoria();
        subTrombones.setNombre("Trombones");
        subTrombones.setImagenUrl("/images/subcategories/trombon.jpg");
        subTrombones.setCategoria(catVientoMetal);
        subCategoriaRepository.save(subTrombones);

        SubCategoria subTrompas = new SubCategoria();
        subTrompas.setNombre("Trompas");
        subTrompas.setImagenUrl("/images/subcategories/trompa.jpg");
        subTrompas.setCategoria(catVientoMetal);
        subCategoriaRepository.save(subTrompas);

        SubCategoria subTubas = new SubCategoria();
        subTubas.setNombre("Tubas");
        subTubas.setImagenUrl("/images/subcategories/tuba.jpg");
        subTubas.setCategoria(catVientoMetal);
        subCategoriaRepository.save(subTubas);

        // ---- TRADICIONALES
        SubCategoria subBandurria = new SubCategoria();
        subBandurria.setNombre("Bandurrias");
        subBandurria.setImagenUrl("/images/subcategories/bandurria.jpg");
        subBandurria.setCategoria(catTradicionales);
        subCategoriaRepository.save(subBandurria);

        SubCategoria subLaud = new SubCategoria();
        subLaud.setNombre("Laúdes");
        subLaud.setImagenUrl("/images/subcategories/laud.jpg");
        subLaud.setCategoria(catTradicionales);
        subCategoriaRepository.save(subLaud);

        SubCategoria subGaitas = new SubCategoria();
        subGaitas.setNombre("Gaitas");
        subGaitas.setImagenUrl("/images/subcategories/gaita.jpg");
        subGaitas.setCategoria(catTradicionales);
        subCategoriaRepository.save(subGaitas);

        // ======================
        // PRODUCTOS
        // ======================
        Producto p1 = new Producto();
        p1.setNombre("Fender Stratocaster");
        p1.setMarca("Fender");
        p1.setStock(5);
        p1.setPrecio(799.99);
        p1.setDescripcion("Guitarra eléctrica clásica con sonido brillante.");
        p1.setImagen("/images/products/stratocaster.jpg");
        p1.setSubCategoria(subGuitElec);
        productoRepository.save(p1);

        Producto p2 = new Producto();
        p2.setNombre("Gibson Les Paul");
        p2.setMarca("Gibson");
        p2.setStock(2);
        p2.setPrecio(1299.99);
        p2.setDescripcion("Guitarra con cuerpo sólido y sustain potente.");
        p2.setImagen("/images/products/lespaul.jpg");
        p2.setSubCategoria(subGuitElec);
        productoRepository.save(p2);

        Producto p3 = new Producto();
        p3.setNombre("Ibanez SR300");
        p3.setMarca("Ibanez");
        p3.setStock(7);
        p3.setPrecio(429.99);
        p3.setDescripcion("Bajo cómodo y versátil para directos y estudio.");
        p3.setImagen("/images/products/ibanez.jpg");
        p3.setSubCategoria(subBajos);
        productoRepository.save(p3);

        Producto p4 = new Producto();
        p4.setNombre("Yamaha P-45");
        p4.setMarca("Yamaha");
        p4.setStock(10);
        p4.setPrecio(399.99);
        p4.setDescripcion("Piano digital 88 teclas con acción de martillo.");
        p4.setImagen("/images/products/yamaha.jpg");
        p4.setSubCategoria(subPianos);
        productoRepository.save(p4);

        Producto p5 = new Producto();
        p5.setNombre("Roland FP-30X");
        p5.setMarca("Roland");
        p5.setStock(6);
        p5.setPrecio(649.99);
        p5.setDescripcion("Piano digital con buen tacto y altavoces potentes.");
        p5.setImagen("/images/products/roland.jpg");
        p5.setSubCategoria(subPianos);
        productoRepository.save(p5);
        
        Producto p6 = new Producto();
        p6.setNombre("PRS SE Custom 24");
        p6.setMarca("PRS");
        p6.setStock(4);
        p6.setPrecio(899.99);
        p6.setDescripcion("Guitarra eléctrica versátil con gran calidad de acabados.");
        p6.setImagen("/images/products/prs.jpg");
        p6.setSubCategoria(subGuitElec);
        productoRepository.save(p6);

        Producto p7 = new Producto();
        p7.setNombre("Jackson Dinky JS32");
        p7.setMarca("Jackson");
        p7.setStock(6);
        p7.setPrecio(349.99);
        p7.setDescripcion("Guitarra eléctrica ideal para rock y metal.");
        p7.setImagen("/images/products/jackson.jpg");
        p7.setSubCategoria(subGuitElec);
        productoRepository.save(p7);

        Producto p8 = new Producto();
        p8.setNombre("Fender Precision Bass");
        p8.setMarca("Fender");
        p8.setStock(3);
        p8.setPrecio(749.99);
        p8.setDescripcion("Bajo clásico con sonido potente y definido.");
        p8.setImagen("/images/products/precisionbass.jpg");
        p8.setSubCategoria(subBajos);
        productoRepository.save(p8);

        Producto p9 = new Producto();
        p9.setNombre("Music Man StingRay");
        p9.setMarca("Ernie Ball");
        p9.setStock(2);
        p9.setPrecio(1399.99);
        p9.setDescripcion("Bajo profesional con gran pegada y presencia.");
        p9.setImagen("/images/products/stingray.jpg");
        p9.setSubCategoria(subBajos);
        productoRepository.save(p9);

        Producto p10 = new Producto();
        p10.setNombre("Casio Privia PX-160");
        p10.setMarca("Casio");
        p10.setStock(8);
        p10.setPrecio(499.99);
        p10.setDescripcion("Piano digital compacto con teclas contrapesadas.");
        p10.setImagen("/images/products/casio.jpg");
        p10.setSubCategoria(subPianos);
        productoRepository.save(p10);

        Producto p11 = new Producto();
        p11.setNombre("Kawai ES110");
        p11.setMarca("Kawai");
        p11.setStock(5);
        p11.setPrecio(699.99);
        p11.setDescripcion("Piano digital con sonido realista y teclado sensible.");
        p11.setImagen("/images/products/kawai.jpg");
        p11.setSubCategoria(subPianos);
        productoRepository.save(p11);

        Producto p12 = new Producto();
        p12.setNombre("Yamaha Arius YDP-144");
        p12.setMarca("Yamaha");
        p12.setStock(3);
        p12.setPrecio(999.99);
        p12.setDescripcion("Piano digital de mueble con sensación acústica.");
        p12.setImagen("/images/products/arius.jpg");
        p12.setSubCategoria(subPianos);
        productoRepository.save(p12);
       
         // ======================
        // CESTAS (FK user_id)
        // ======================
        Cesta cesta1 = new Cesta();
        cesta1.setUserId(u1.getId());
        cestaRepository.save(cesta1);

        Cesta cesta2 = new Cesta();
        cesta2.setUserId(u2.getId());
        cestaRepository.save(cesta2);
        
        // ======================
        // LÍNEAS DE CESTA
        // ======================
        LineaCesta l1 = new LineaCesta();
        l1.setCesta(cesta1);
        l1.setProducto(p1);
        l1.setCantidad(1);
        l1.setPrecioUnitario(p1.getPrecio());
        lineaCestaRepository.save(l1);

        LineaCesta l2 = new LineaCesta();
        l2.setCesta(cesta1);
        l2.setProducto(p4);
        l2.setCantidad(1);
        l2.setPrecioUnitario(p4.getPrecio());
        lineaCestaRepository.save(l2);

        LineaCesta l3 = new LineaCesta();
        l3.setCesta(cesta2);
        l3.setProducto(p5);
        l3.setCantidad(2);
        l3.setPrecioUnitario(p5.getPrecio());
        lineaCestaRepository.save(l3);

        System.out.println("Categorias creadas: " + categoriaRepository.count());
        System.out.println("Instrumentos creados: " + productoRepository.count());
        System.out.println("Pedidos creados: " + cestaRepository.count());
        System.out.println("Lineas de pedido creadas: " + lineaCestaRepository.count());
    }
}
