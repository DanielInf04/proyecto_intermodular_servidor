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
        SubCategoria subBateriasAcusticas = new SubCategoria();
        subBateriasAcusticas.setNombre("Baterías acústicas");
        subBateriasAcusticas.setImagenUrl("/images/subcategories/bateria_acustica.jpg");
        subBateriasAcusticas.setCategoria(catPercusion);
        subCategoriaRepository.save(subBateriasAcusticas);

        SubCategoria subBateriasElectronicas = new SubCategoria();
        subBateriasElectronicas.setNombre("Baterías electrónicas");
        subBateriasElectronicas.setImagenUrl("/images/subcategories/bateria_electronica.jpg");
        subBateriasElectronicas.setCategoria(catPercusion);
        subCategoriaRepository.save(subBateriasElectronicas);

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
        
        Producto p13 = new Producto();
        p13.setNombre("Fender Player Telecaster");
        p13.setMarca("Fender");
        p13.setStock(5);
        p13.setPrecio(749.99);
        p13.setDescripcion("Guitarra eléctrica versátil con el clásico twang de Telecaster.");
        p13.setImagen("/images/products/fender_player_telecaster.jpg");
        p13.setSubCategoria(subGuitElec);
        productoRepository.save(p13);

        Producto p14 = new Producto();
        p14.setNombre("Fender Player Jazzmaster");
        p14.setMarca("Fender");
        p14.setStock(4);
        p14.setPrecio(829.99);
        p14.setDescripcion("Guitarra con sonido cálido y estética vintage alternativa.");
        p14.setImagen("/images/products/fender_jazzmaster.jpg");
        p14.setSubCategoria(subGuitElec);
        productoRepository.save(p14);
        
        Producto p15 = new Producto();
        p15.setNombre("Fender Mustang Player");
        p15.setMarca("Fender");
        p15.setStock(6);
        p15.setPrecio(699.99);
        p15.setDescripcion("Modelo compacto y cómodo ideal para estilos indie y rock.");
        p15.setImagen("/images/products/fender_mustang.jpg");
        p15.setSubCategoria(subGuitElec);
        productoRepository.save(p15);

        Producto p16 = new Producto();
        p16.setNombre("Gibson SG Standard");
        p16.setMarca("Gibson");
        p16.setStock(3);
        p16.setPrecio(1399.99);
        p16.setDescripcion("Guitarra ligera con potente sustain y acceso cómodo a trastes altos.");
        p16.setImagen("/images/products/gibson_sg_standard.jpg");
        p16.setSubCategoria(subGuitElec);
        productoRepository.save(p16);

        Producto p17 = new Producto();
        p17.setNombre("Gibson Flying V");
        p17.setMarca("Gibson");
        p17.setStock(2);
        p17.setPrecio(1599.99);
        p17.setDescripcion("Diseño icónico con sonido agresivo ideal para rock y metal.");
        p17.setImagen("/images/products/gibson_flying_v.jpg");
        p17.setSubCategoria(subGuitElec);
        productoRepository.save(p17);

        Producto p18 = new Producto();
        p18.setNombre("Epiphone Les Paul Standard 60s");
        p18.setMarca("Epiphone");
        p18.setStock(7);
        p18.setPrecio(599.99);
        p18.setDescripcion("Versión asequible del clásico Les Paul con gran sustain.");
        p18.setImagen("/images/products/epiphone_les_paul_standard_60s.jpg");
        p18.setSubCategoria(subGuitElec);
        productoRepository.save(p18);

        Producto p19 = new Producto();
        p19.setNombre("Epiphone SG Standard");
        p19.setMarca("Epiphone");
        p19.setStock(6);
        p19.setPrecio(499.99);
        p19.setDescripcion("Modelo ligero con sonido potente y diseño clásico.");
        p19.setImagen("/images/products/epiphone_sg_standard.jpg");
        p19.setSubCategoria(subGuitElec);
        productoRepository.save(p19);

        Producto p20 = new Producto();
        p20.setNombre("PRS SE McCarty 594");
        p20.setMarca("PRS");
        p20.setStock(4);
        p20.setPrecio(899.99);
        p20.setDescripcion("Guitarra versátil con excelente acabado y tono vintage moderno.");
        p20.setImagen("/images/products/prs_se_mccarty_594.jpg");
        p20.setSubCategoria(subGuitElec);
        productoRepository.save(p20);

        Producto p21 = new Producto();
        p21.setNombre("Ibanez RG550");
        p21.setMarca("Ibanez");
        p21.setStock(5);
        p21.setPrecio(999.99);
        p21.setDescripcion("Modelo rápido y cómodo ideal para shred y metal técnico.");
        p21.setImagen("/images/products/ibanez_rg550.jpg");
        p21.setSubCategoria(subGuitElec);
        productoRepository.save(p21);

        Producto p22 = new Producto();
        p22.setNombre("Ibanez AZES40");
        p22.setMarca("Ibanez");
        p22.setStock(8);
        p22.setPrecio(349.99);
        p22.setDescripcion("Guitarra versátil con gran comodidad para principiantes y avanzados.");
        p22.setImagen("/images/products/ibanez_azes40.jpg");
        p22.setSubCategoria(subGuitElec);
        productoRepository.save(p22);

        Producto p23 = new Producto();
        p23.setNombre("ESP LTD EC-256");
        p23.setMarca("ESP LTD");
        p23.setStock(6);
        p23.setPrecio(459.99);
        p23.setDescripcion("Guitarra de cuerpo sólido con sonido potente y acabado elegante.");
        p23.setImagen("/images/products/esp_ltd_ec256.jpg");
        p23.setSubCategoria(subGuitElec);
        productoRepository.save(p23);

        Producto p24 = new Producto();
        p24.setNombre("ESP LTD M-1000");
        p24.setMarca("ESP LTD");
        p24.setStock(3);
        p24.setPrecio(1099.99);
        p24.setDescripcion("Modelo profesional orientado a estilos modernos y metal.");
        p24.setImagen("/images/products/esp_ltd_m1000.jpg");
        p24.setSubCategoria(subGuitElec);
        productoRepository.save(p24);

        Producto p25 = new Producto();
        p25.setNombre("Schecter Omen-6");
        p25.setMarca("Schecter");
        p25.setStock(7);
        p25.setPrecio(399.99);
        p25.setDescripcion("Guitarra ideal para rock y metal con gran relación calidad-precio.");
        p25.setImagen("/images/products/schecter_omen6.jpg");
        p25.setSubCategoria(subGuitElec);
        productoRepository.save(p25);

        Producto p26 = new Producto();
        p26.setNombre("Schecter C-6 Deluxe");
        p26.setMarca("Schecter");
        p26.setStock(6);
        p26.setPrecio(449.99);
        p26.setDescripcion("Modelo moderno con buena estabilidad y sonido definido.");
        p26.setImagen("/images/products/schecter_c6_deluxe.jpg");
        p26.setSubCategoria(subGuitElec);
        productoRepository.save(p26);

        Producto p27 = new Producto();
        p27.setNombre("Yamaha Pacifica 112V");
        p27.setMarca("Yamaha");
        p27.setStock(9);
        p27.setPrecio(329.99);
        p27.setDescripcion("Guitarra versátil perfecta para iniciarse en la eléctrica.");
        p27.setImagen("/images/products/yamaha_pacifica_112v.jpg");
        p27.setSubCategoria(subGuitElec);
        productoRepository.save(p27);

        Producto p28 = new Producto();
        p28.setNombre("Charvel Pro-Mod DK24");
        p28.setMarca("Charvel");
        p28.setStock(3);
        p28.setPrecio(1049.99);
        p28.setDescripcion("Guitarra moderna de alto rendimiento con gran comodidad.");
        p28.setImagen("/images/products/charvel_dk24.jpg");
        p28.setSubCategoria(subGuitElec);
        productoRepository.save(p28);
    
        Producto p29 = new Producto();
        p29.setNombre("Fender Player Jazz Bass");
        p29.setMarca("Fender");
        p29.setStock(4);
        p29.setPrecio(879.99);
        p29.setDescripcion("Bajo versátil con sonido brillante y gran definición.");
        p29.setImagen("/images/products/fender_player_jazz_bass.jpg");
        p29.setSubCategoria(subBajos);
        productoRepository.save(p29);

        Producto p30 = new Producto();
        p30.setNombre("Fender Aerodyne Jazz Bass");
        p30.setMarca("Fender");
        p30.setStock(3);
        p30.setPrecio(1099.99);
        p30.setDescripcion("Diseño moderno y ligero con tono potente y equilibrado.");
        p30.setImagen("/images/products/fender_aerodyne_jazz_bass.jpg");
        p30.setSubCategoria(subBajos);
        productoRepository.save(p30);

        Producto p31 = new Producto();
        p31.setNombre("Squier Classic Vibe 70s Jazz Bass");
        p31.setMarca("Squier");
        p31.setStock(6);
        p31.setPrecio(449.99);
        p31.setDescripcion("Inspirado en los años 70 con sonido vintage y gran comodidad.");
        p31.setImagen("/images/products/squier_classic_vibe_70s_jazz_bass.jpg");
        p31.setSubCategoria(subBajos);
        productoRepository.save(p31);

        Producto p32 = new Producto();
        p32.setNombre("Squier Classic Vibe 60s Precision Bass");
        p32.setMarca("Squier");
        p32.setStock(5);
        p32.setPrecio(429.99);
        p32.setDescripcion("Sonido clásico tipo Precision con excelente relación calidad-precio.");
        p32.setImagen("/images/products/squier_classic_vibe_60s_precision_bass.jpg");
        p32.setSubCategoria(subBajos);
        productoRepository.save(p32);

        Producto p33 = new Producto();
        p33.setNombre("Ibanez SR500E");
        p33.setMarca("Ibanez");
        p33.setStock(4);
        p33.setPrecio(699.99);
        p33.setDescripcion("Bajo ligero y cómodo con electrónica activa versátil.");
        p33.setImagen("/images/products/ibanez_sr500e.jpg");
        p33.setSubCategoria(subBajos);
        productoRepository.save(p33);

        Producto p34 = new Producto();
        p34.setNombre("Ibanez BTB745");
        p34.setMarca("Ibanez");
        p34.setStock(3);
        p34.setPrecio(999.99);
        p34.setDescripcion("Bajo de 5 cuerdas con gran sustain y sonido profundo.");
        p34.setImagen("/images/products/ibanez_btb745.jpg");
        p34.setSubCategoria(subBajos);
        productoRepository.save(p34);

        Producto p35 = new Producto();
        p35.setNombre("Yamaha TRBX304");
        p35.setMarca("Yamaha");
        p35.setStock(7);
        p35.setPrecio(399.99);
        p35.setDescripcion("Modelo cómodo y versátil ideal para directo y estudio.");
        p35.setImagen("/images/products/yamaha_trbx304.jpg");
        p35.setSubCategoria(subBajos);
        productoRepository.save(p35);

        Producto p36 = new Producto();
        p36.setNombre("Yamaha TRBX504");
        p36.setMarca("Yamaha");
        p36.setStock(5);
        p36.setPrecio(579.99);
        p36.setDescripcion("Bajo activo con excelente construcción y tono equilibrado.");
        p36.setImagen("/images/products/yamaha_trbx504.jpg");
        p36.setSubCategoria(subBajos);
        productoRepository.save(p36);

        Producto p37 = new Producto();
        p37.setNombre("Cort Action DLX");
        p37.setMarca("Cort");
        p37.setStock(6);
        p37.setPrecio(349.99);
        p37.setDescripcion("Bajo activo con gran pegada y cómodo para principiantes.");
        p37.setImagen("/images/products/cort_action_dlx.jpg");
        p37.setSubCategoria(subBajos);
        productoRepository.save(p37);

        Producto p38 = new Producto();
        p38.setNombre("Warwick RockBass Corvette");
        p38.setMarca("Warwick");
        p38.setStock(2);
        p38.setPrecio(849.99);
        p38.setDescripcion("Bajo con sonido profundo y construcción alemana de calidad.");
        p38.setImagen("/images/products/warwick_rockbass_corvette.jpg");
        p38.setSubCategoria(subBajos);
        productoRepository.save(p38);

        Producto p39 = new Producto();
        p39.setNombre("Schecter Stiletto Studio-4");
        p39.setMarca("Schecter");
        p39.setStock(4);
        p39.setPrecio(649.99);
        p39.setDescripcion("Bajo moderno con excelente definición y estética elegante.");
        p39.setImagen("/images/products/schecter_stiletto_studio4.jpg");
        p39.setSubCategoria(subBajos);
        productoRepository.save(p39);

        Producto p40 = new Producto();
        p40.setNombre("ESP LTD B-204SM");
        p40.setMarca("ESP LTD");
        p40.setStock(5);
        p40.setPrecio(499.99);
        p40.setDescripcion("Bajo activo con gran ataque y acabado flameado atractivo.");
        p40.setImagen("/images/products/esp_ltd_b204sm.jpg");
        p40.setSubCategoria(subBajos);
        productoRepository.save(p40);

        Producto p41 = new Producto();
        p41.setNombre("Sterling by Music Man Ray4");
        p41.setMarca("Sterling");
        p41.setStock(6);
        p41.setPrecio(429.99);
        p41.setDescripcion("Versión asequible del StingRay con sonido potente y definido.");
        p41.setImagen("/images/products/sterling_ray4.jpg");
        p41.setSubCategoria(subBajos);
        productoRepository.save(p41);

        Producto p42 = new Producto();
        p42.setNombre("Hofner Ignition Violin Bass");
        p42.setMarca("Hofner");
        p42.setStock(3);
        p42.setPrecio(379.99);
        p42.setDescripcion("Bajo estilo violín con sonido cálido y clásico.");
        p42.setImagen("/images/products/hofner_ignition_violin_bass.jpg");
        p42.setSubCategoria(subBajos);
        productoRepository.save(p42);

        Producto p43 = new Producto();
        p43.setNombre("G&L Tribute JB-2");
        p43.setMarca("G&L");
        p43.setStock(3);
        p43.setPrecio(699.99);
        p43.setDescripcion("Bajo tipo Jazz con gran claridad y construcción sólida.");
        p43.setImagen("/images/products/gl_tribute_jb2.jpg");
        p43.setSubCategoria(subBajos);
        productoRepository.save(p43);

        Producto p44 = new Producto();
        p44.setNombre("Marcus Miller V7");
        p44.setMarca("Marcus Miller");
        p44.setStock(4);
        p44.setPrecio(599.99);
        p44.setDescripcion("Bajo moderno con electrónica activa y sonido versátil.");
        p44.setImagen("/images/products/marcus_miller_v7.jpg");
        p44.setSubCategoria(subBajos);
        productoRepository.save(p44);
        
        Producto p45 = new Producto();
        p45.setNombre("Yamaha F310");
        p45.setMarca("Yamaha");
        p45.setStock(8);
        p45.setPrecio(159.99);
        p45.setDescripcion("Guitarra acústica económica ideal para principiantes.");
        p45.setImagen("/images/products/yamaha_f310.jpg");
        p45.setSubCategoria(subGuitAcust);
        productoRepository.save(p45);

        Producto p46 = new Producto();
        p46.setNombre("Yamaha FG800");
        p46.setMarca("Yamaha");
        p46.setStock(6);
        p46.setPrecio(249.99);
        p46.setDescripcion("Modelo dreadnought con sonido equilibrado y gran proyección.");
        p46.setImagen("/images/products/yamaha_fg800.jpg");
        p46.setSubCategoria(subGuitAcust);
        productoRepository.save(p46);

        Producto p47 = new Producto();
        p47.setNombre("Yamaha FS800");
        p47.setMarca("Yamaha");
        p47.setStock(5);
        p47.setPrecio(239.99);
        p47.setDescripcion("Cuerpo compacto con tono claro y cómodo de tocar.");
        p47.setImagen("/images/products/yamaha_fs800.jpg");
        p47.setSubCategoria(subGuitAcust);
        productoRepository.save(p47);

        Producto p48 = new Producto();
        p48.setNombre("Fender CD-60S");
        p48.setMarca("Fender");
        p48.setStock(7);
        p48.setPrecio(229.99);
        p48.setDescripcion("Acústica dreadnought con tapa maciza y sonido potente.");
        p48.setImagen("/images/products/fender_cd60s.jpg");
        p48.setSubCategoria(subGuitAcust);
        productoRepository.save(p48);

        Producto p49 = new Producto();
        p49.setNombre("Fender FA-125");
        p49.setMarca("Fender");
        p49.setStock(9);
        p49.setPrecio(179.99);
        p49.setDescripcion("Modelo asequible con buena resonancia y fácil ejecución.");
        p49.setImagen("/images/products/fender_fa125.jpg");
        p49.setSubCategoria(subGuitAcust);
        productoRepository.save(p49);

        Producto p50 = new Producto();
        p50.setNombre("Epiphone DR-100");
        p50.setMarca("Epiphone");
        p50.setStock(6);
        p50.setPrecio(189.99);
        p50.setDescripcion("Clásica dreadnought con sonido cálido y equilibrado.");
        p50.setImagen("/images/products/epiphone_dr100.jpg");
        p50.setSubCategoria(subGuitAcust);
        productoRepository.save(p50);

        Producto p51 = new Producto();
        p51.setNombre("Epiphone Hummingbird Studio");
        p51.setMarca("Epiphone");
        p51.setStock(4);
        p51.setPrecio(399.99);
        p51.setDescripcion("Modelo icónico con diseño elegante y tono profundo.");
        p51.setImagen("/images/products/epiphone_hummingbird_studio.jpg");
        p51.setSubCategoria(subGuitAcust);
        productoRepository.save(p51);

        Producto p52 = new Producto();
        p52.setNombre("Taylor Academy 10e");
        p52.setMarca("Taylor");
        p52.setStock(3);
        p52.setPrecio(699.99);
        p52.setDescripcion("Acústica electroacústica con gran comodidad y sonido brillante.");
        p52.setImagen("/images/products/taylor_academy_10e.jpg");
        p52.setSubCategoria(subGuitAcust);
        productoRepository.save(p52);

        Producto p53 = new Producto();
        p53.setNombre("Taylor 114ce");
        p53.setMarca("Taylor");
        p53.setStock(3);
        p53.setPrecio(849.99);
        p53.setDescripcion("Electroacústica versátil con excelente proyección.");
        p53.setImagen("/images/products/taylor_114ce.jpg");
        p53.setSubCategoria(subGuitAcust);
        productoRepository.save(p53);

        Producto p54 = new Producto();
        p54.setNombre("Martin D-10E");
        p54.setMarca("Martin");
        p54.setStock(2);
        p54.setPrecio(899.99);
        p54.setDescripcion("Acústica profesional con tono profundo y gran sustain.");
        p54.setImagen("/images/products/martin_d10e.jpg");
        p54.setSubCategoria(subGuitAcust);
        productoRepository.save(p54);

        Producto p55 = new Producto();
        p55.setNombre("Martin DX1AE");
        p55.setMarca("Martin");
        p55.setStock(2);
        p55.setPrecio(799.99);
        p55.setDescripcion("Electroacústica con sonido claro y construcción robusta.");
        p55.setImagen("/images/products/martin_dx1ae.jpg");
        p55.setSubCategoria(subGuitAcust);
        productoRepository.save(p55);

        Producto p56 = new Producto();
        p56.setNombre("Takamine GD20");
        p56.setMarca("Takamine");
        p56.setStock(5);
        p56.setPrecio(329.99);
        p56.setDescripcion("Guitarra acústica con excelente relación calidad-precio.");
        p56.setImagen("/images/products/takamine_gd20.jpg");
        p56.setSubCategoria(subGuitAcust);
        productoRepository.save(p56);

        Producto p57 = new Producto();
        p57.setNombre("Takamine GN93CE");
        p57.setMarca("Takamine");
        p57.setStock(3);
        p57.setPrecio(549.99);
        p57.setDescripcion("Electroacústica con gran definición y estética elegante.");
        p57.setImagen("/images/products/takamine_gn93ce.jpg");
        p57.setSubCategoria(subGuitAcust);
        productoRepository.save(p57);

        Producto p58 = new Producto();
        p58.setNombre("Ibanez AW54");
        p58.setMarca("Ibanez");
        p58.setStock(6);
        p58.setPrecio(279.99);
        p58.setDescripcion("Modelo con tapa maciza y sonido cálido.");
        p58.setImagen("/images/products/ibanez_aw54.jpg");
        p58.setSubCategoria(subGuitAcust);
        productoRepository.save(p58);

        Producto p59 = new Producto();
        p59.setNombre("Alvarez AD60");
        p59.setMarca("Alvarez");
        p59.setStock(4);
        p59.setPrecio(399.99);
        p59.setDescripcion("Acústica dreadnought con excelente proyección y claridad.");
        p59.setImagen("/images/products/alvarez_ad60.jpg");
        p59.setSubCategoria(subGuitAcust);
        productoRepository.save(p59);
        
              Producto p60 = new Producto();
        p60.setNombre("Kala KA-15S");
        p60.setMarca("Kala");
        p60.setStock(10);
        p60.setPrecio(79.99);
        p60.setDescripcion("Ukelele soprano ideal para principiantes con sonido brillante.");
        p60.setImagen("/images/products/kala_ka15s.jpg");
        p60.setSubCategoria(subUkeleles);
        productoRepository.save(p60);

        Producto p61 = new Producto();
        p61.setNombre("Kala KA-C");
        p61.setMarca("Kala");
        p61.setStock(8);
        p61.setPrecio(99.99);
        p61.setDescripcion("Ukelele concierto con mayor proyección y comodidad.");
        p61.setImagen("/images/products/kala_kac.jpg");
        p61.setSubCategoria(subUkeleles);
        productoRepository.save(p61);

        Producto p62 = new Producto();
        p62.setNombre("Kala KA-T");
        p62.setMarca("Kala");
        p62.setStock(6);
        p62.setPrecio(129.99);
        p62.setDescripcion("Modelo tenor con sonido más profundo y mayor volumen.");
        p62.setImagen("/images/products/kala_kat.jpg");
        p62.setSubCategoria(subUkeleles);
        productoRepository.save(p62);

        Producto p63 = new Producto();
        p63.setNombre("Lanikai LU-21");
        p63.setMarca("Lanikai");
        p63.setStock(9);
        p63.setPrecio(69.99);
        p63.setDescripcion("Ukelele soprano clásico con tono cálido y equilibrado.");
        p63.setImagen("/images/products/lanikai_lu21.jpg");
        p63.setSubCategoria(subUkeleles);
        productoRepository.save(p63);

        Producto p64 = new Producto();
        p64.setNombre("Lanikai CK-C");
        p64.setMarca("Lanikai");
        p64.setStock(7);
        p64.setPrecio(119.99);
        p64.setDescripcion("Ukelele concierto con cuerpo de caoba y gran resonancia.");
        p64.setImagen("/images/products/lanikai_ckc.jpg");
        p64.setSubCategoria(subUkeleles);
        productoRepository.save(p64);

        Producto p65 = new Producto();
        p65.setNombre("Mahalo MR1");
        p65.setMarca("Mahalo");
        p65.setStock(12);
        p65.setPrecio(49.99);
        p65.setDescripcion("Modelo soprano económico ideal para iniciarse.");
        p65.setImagen("/images/products/mahalo_mr1.jpg");
        p65.setSubCategoria(subUkeleles);
        productoRepository.save(p65);

        Producto p66 = new Producto();
        p66.setNombre("Mahalo MJ1T");
        p66.setMarca("Mahalo");
        p66.setStock(8);
        p66.setPrecio(89.99);
        p66.setDescripcion("Ukelele tenor con buen volumen y sonido equilibrado.");
        p66.setImagen("/images/products/mahalo_mj1t.jpg");
        p66.setSubCategoria(subUkeleles);
        productoRepository.save(p66);

        Producto p67 = new Producto();
        p67.setNombre("Fender Venice Soprano");
        p67.setMarca("Fender");
        p67.setStock(6);
        p67.setPrecio(89.99);
        p67.setDescripcion("Diseño moderno y sonido brillante ideal para estilos actuales.");
        p67.setImagen("/images/products/fender_venice_soprano.jpg");
        p67.setSubCategoria(subUkeleles);
        productoRepository.save(p67);

        Producto p68 = new Producto();
        p68.setNombre("Fender Zuma Concert");
        p68.setMarca("Fender");
        p68.setStock(5);
        p68.setPrecio(109.99);
        p68.setDescripcion("Ukelele concierto con estética elegante y tono definido.");
        p68.setImagen("/images/products/fender_zuma_concert.jpg");
        p68.setSubCategoria(subUkeleles);
        productoRepository.save(p68);

        Producto p69 = new Producto();
        p69.setNombre("Ibanez UEW12E");
        p69.setMarca("Ibanez");
        p69.setStock(4);
        p69.setPrecio(199.99);
        p69.setDescripcion("Ukelele electroacústico con acabado exótico y gran proyección.");
        p69.setImagen("/images/products/ibanez_uew12e.jpg");
        p69.setSubCategoria(subUkeleles);
        productoRepository.save(p69);

        Producto p70 = new Producto();
        p70.setNombre("Cordoba 15CM");
        p70.setMarca("Cordoba");
        p70.setStock(7);
        p70.setPrecio(89.99);
        p70.setDescripcion("Modelo concierto con sonido cálido y excelente construcción.");
        p70.setImagen("/images/products/cordoba_15cm.jpg");
        p70.setSubCategoria(subUkeleles);
        productoRepository.save(p70);

        Producto p71 = new Producto();
        p71.setNombre("Cordoba 20TM-CE");
        p71.setMarca("Cordoba");
        p71.setStock(4);
        p71.setPrecio(219.99);
        p71.setDescripcion("Ukelele tenor electroacústico con gran definición sonora.");
        p71.setImagen("/images/products/cordoba_20tmce.jpg");
        p71.setSubCategoria(subUkeleles);
        productoRepository.save(p71);

        Producto p72 = new Producto();
        p72.setNombre("Flight NUS310");
        p72.setMarca("Flight");
        p72.setStock(10);
        p72.setPrecio(59.99);
        p72.setDescripcion("Ukelele soprano ligero y resistente ideal para principiantes.");
        p72.setImagen("/images/products/flight_nus310.jpg");
        p72.setSubCategoria(subUkeleles);
        productoRepository.save(p72);

        Producto p73 = new Producto();
        p73.setNombre("Flight DUC380 CEQ");
        p73.setMarca("Flight");
        p73.setStock(5);
        p73.setPrecio(179.99);
        p73.setDescripcion("Modelo concierto electroacústico con acabado llamativo.");
        p73.setImagen("/images/products/flight_duc380_ceq.jpg");
        p73.setSubCategoria(subUkeleles);
        productoRepository.save(p73);

        Producto p74 = new Producto();
        p74.setNombre("Ortega RU5");
        p74.setMarca("Ortega");
        p74.setStock(6);
        p74.setPrecio(99.99);
        p74.setDescripcion("Ukelele soprano con sonido equilibrado y gran comodidad.");
        p74.setImagen("/images/products/ortega_ru5.jpg");
        p74.setSubCategoria(subUkeleles);
        productoRepository.save(p74);
        
        Producto p75 = new Producto();
        p75.setNombre("Stentor Student I 4/4");
        p75.setMarca("Stentor");
        p75.setStock(6);
        p75.setPrecio(189.99);
        p75.setDescripcion("Violín de estudio ideal para principiantes con buena afinación.");
        p75.setImagen("/images/products/stentor_student_1.jpg");
        p75.setSubCategoria(subViolines);
        productoRepository.save(p75);

        Producto p76 = new Producto();
        p76.setNombre("Stentor Student II 4/4");
        p76.setMarca("Stentor");
        p76.setStock(5);
        p76.setPrecio(249.99);
        p76.setDescripcion("Modelo mejorado para estudiantes con sonido más definido.");
        p76.setImagen("/images/products/stentor_student_2.jpg");
        p76.setSubCategoria(subViolines);
        productoRepository.save(p76);

        Producto p77 = new Producto();
        p77.setNombre("Yamaha V3SKA 4/4");
        p77.setMarca("Yamaha");
        p77.setStock(4);
        p77.setPrecio(329.99);
        p77.setDescripcion("Violín completo para estudiante con excelente calidad de construcción.");
        p77.setImagen("/images/products/yamaha_v3ska.jpg");
        p77.setSubCategoria(subViolines);
        productoRepository.save(p77);

        Producto p78 = new Producto();
        p78.setNombre("Yamaha V5SA 4/4");
        p78.setMarca("Yamaha");
        p78.setStock(3);
        p78.setPrecio(459.99);
        p78.setDescripcion("Modelo intermedio con sonido cálido y gran proyección.");
        p78.setImagen("/images/products/yamaha_v5sa.jpg");
        p78.setSubCategoria(subViolines);
        productoRepository.save(p78);

        Producto p79 = new Producto();
        p79.setNombre("Cremona SV-75 4/4");
        p79.setMarca("Cremona");
        p79.setStock(5);
        p79.setPrecio(219.99);
        p79.setDescripcion("Violín de estudio con buena resonancia y acabado clásico.");
        p79.setImagen("/images/products/cremona_sv75.jpg");
        p79.setSubCategoria(subViolines);
        productoRepository.save(p79);

        Producto p80 = new Producto();
        p80.setNombre("Cremona SV-175 4/4");
        p80.setMarca("Cremona");
        p80.setStock(4);
        p80.setPrecio(349.99);
        p80.setDescripcion("Modelo intermedio con mejor proyección y materiales seleccionados.");
        p80.setImagen("/images/products/cremona_sv175.jpg");
        p80.setSubCategoria(subViolines);
        productoRepository.save(p80);

        Producto p81 = new Producto();
        p81.setNombre("Eastman VL80 4/4");
        p81.setMarca("Eastman");
        p81.setStock(3);
        p81.setPrecio(299.99);
        p81.setDescripcion("Violín artesanal de estudio con sonido equilibrado.");
        p81.setImagen("/images/products/eastman_vl80.jpg");
        p81.setSubCategoria(subViolines);
        productoRepository.save(p81);

        Producto p82 = new Producto();
        p82.setNombre("Eastman VL100 4/4");
        p82.setMarca("Eastman");
        p82.setStock(3);
        p82.setPrecio(499.99);
        p82.setDescripcion("Modelo intermedio con mayor riqueza tonal.");
        p82.setImagen("/images/products/eastman_vl100.jpg");
        p82.setSubCategoria(subViolines);
        productoRepository.save(p82);

        Producto p83 = new Producto();
        p83.setNombre("Höfner AS-060 4/4");
        p83.setMarca("Hofner");
        p83.setStock(4);
        p83.setPrecio(279.99);
        p83.setDescripcion("Violín clásico con sonido cálido y buena respuesta.");
        p83.setImagen("/images/products/hofner_as060.jpg");
        p83.setSubCategoria(subViolines);
        productoRepository.save(p83);

        Producto p84 = new Producto();
        p84.setNombre("GEWA Allegro 4/4");
        p84.setMarca("GEWA");
        p84.setStock(5);
        p84.setPrecio(239.99);
        p84.setDescripcion("Violín de estudio con excelente relación calidad-precio.");
        p84.setImagen("/images/products/gewa_allegro.jpg");
        p84.setSubCategoria(subViolines);
        productoRepository.save(p84);

        Producto p85 = new Producto();
        p85.setNombre("GEWA Maestro 4/4");
        p85.setMarca("GEWA");
        p85.setStock(2);
        p85.setPrecio(599.99);
        p85.setDescripcion("Modelo avanzado con mayor proyección y riqueza sonora.");
        p85.setImagen("/images/products/gewa_maestro.jpg");
        p85.setSubCategoria(subViolines);
        productoRepository.save(p85);

        Producto p86 = new Producto();
        p86.setNombre("NS Design WAV4");
        p86.setMarca("NS Design");
        p86.setStock(2);
        p86.setPrecio(699.99);
        p86.setDescripcion("Violín eléctrico moderno ideal para escenario.");
        p86.setImagen("/images/products/ns_design_wav4.jpg");
        p86.setSubCategoria(subViolines);
        productoRepository.save(p86);

        Producto p87 = new Producto();
        p87.setNombre("Yamaha YEV-104");
        p87.setMarca("Yamaha");
        p87.setStock(2);
        p87.setPrecio(749.99);
        p87.setDescripcion("Violín eléctrico con diseño elegante y sonido natural.");
        p87.setImagen("/images/products/yamaha_yev104.jpg");
        p87.setSubCategoria(subViolines);
        productoRepository.save(p87);

        Producto p88 = new Producto();
        p88.setNombre("Stagg EVN 4/4");
        p88.setMarca("Stagg");
        p88.setStock(4);
        p88.setPrecio(289.99);
        p88.setDescripcion("Violín eléctrico asequible ideal para iniciarse en amplificación.");
        p88.setImagen("/images/products/stagg_evn.jpg");
        p88.setSubCategoria(subViolines);
        productoRepository.save(p88);

        Producto p89 = new Producto();
        p89.setNombre("Harley Benton HBV 870 4/4");
        p89.setMarca("Harley Benton");
        p89.setStock(6);
        p89.setPrecio(149.99);
        p89.setDescripcion("Violín de estudio económico perfecto para principiantes.");
        p89.setImagen("/images/products/harley_benton_hbv870.jpg");
        p89.setSubCategoria(subViolines);
        productoRepository.save(p89);
        
        Producto p90 = new Producto();
        p90.setNombre("Yamaha P-125");
        p90.setMarca("Yamaha");
        p90.setStock(5);
        p90.setPrecio(649.99);
        p90.setDescripcion("Piano digital compacto con sonido realista y teclas contrapesadas.");
        p90.setImagen("/images/products/yamaha_p125.jpg");
        p90.setSubCategoria(subPianos);
        productoRepository.save(p90);

        Producto p91 = new Producto();
        p91.setNombre("Yamaha P-225");
        p91.setMarca("Yamaha");
        p91.setStock(4);
        p91.setPrecio(749.99);
        p91.setDescripcion("Modelo portátil con acción mejorada y sonido profesional.");
        p91.setImagen("/images/products/yamaha_p225.jpg");
        p91.setSubCategoria(subPianos);
        productoRepository.save(p91);

        Producto p92 = new Producto();
        p92.setNombre("Yamaha Clavinova CLP-735");
        p92.setMarca("Yamaha");
        p92.setStock(3);
        p92.setPrecio(1499.99);
        p92.setDescripcion("Piano digital de mueble con sensación y sonido de concierto.");
        p92.setImagen("/images/products/yamaha_clp735.jpg");
        p92.setSubCategoria(subPianos);
        productoRepository.save(p92);

        Producto p93 = new Producto();
        p93.setNombre("Roland FP-60X");
        p93.setMarca("Roland");
        p93.setStock(3);
        p93.setPrecio(1099.99);
        p93.setDescripcion("Piano portátil con sonido SuperNATURAL y altavoces potentes.");
        p93.setImagen("/images/products/roland_fp60x.jpg");
        p93.setSubCategoria(subPianos);
        productoRepository.save(p93);

        Producto p94 = new Producto();
        p94.setNombre("Roland FP-E50");
        p94.setMarca("Roland");
        p94.setStock(4);
        p94.setPrecio(999.99);
        p94.setDescripcion("Piano digital versátil con funciones de acompañamiento.");
        p94.setImagen("/images/products/roland_fpe50.jpg");
        p94.setSubCategoria(subPianos);
        productoRepository.save(p94);

        Producto p95 = new Producto();
        p95.setNombre("Casio PX-S1100");
        p95.setMarca("Casio");
        p95.setStock(6);
        p95.setPrecio(699.99);
        p95.setDescripcion("Diseño ultracompacto con teclado Smart Scaled Hammer Action.");
        p95.setImagen("/images/products/casio_pxs1100.jpg");
        p95.setSubCategoria(subPianos);
        productoRepository.save(p95);

        Producto p96 = new Producto();
        p96.setNombre("Casio PX-S3100");
        p96.setMarca("Casio");
        p96.setStock(5);
        p96.setPrecio(899.99);
        p96.setDescripcion("Piano digital con múltiples sonidos y funciones avanzadas.");
        p96.setImagen("/images/products/casio_pxs3100.jpg");
        p96.setSubCategoria(subPianos);
        productoRepository.save(p96);

        Producto p97 = new Producto();
        p97.setNombre("Kawai ES120");
        p97.setMarca("Kawai");
        p97.setStock(4);
        p97.setPrecio(799.99);
        p97.setDescripcion("Modelo portátil con acción Responsive Hammer Compact.");
        p97.setImagen("/images/products/kawai_es120.jpg");
        p97.setSubCategoria(subPianos);
        productoRepository.save(p97);

        Producto p98 = new Producto();
        p98.setNombre("Kawai CN29");
        p98.setMarca("Kawai");
        p98.setStock(3);
        p98.setPrecio(1199.99);
        p98.setDescripcion("Piano digital de mueble con excelente realismo sonoro.");
        p98.setImagen("/images/products/kawai_cn29.jpg");
        p98.setSubCategoria(subPianos);
        productoRepository.save(p98);

        Producto p99 = new Producto();
        p99.setNombre("Korg B2");
        p99.setMarca("Korg");
        p99.setStock(7);
        p99.setPrecio(499.99);
        p99.setDescripcion("Piano digital sencillo con sonido claro y potente.");
        p99.setImagen("/images/products/korg_b2.jpg");
        p99.setSubCategoria(subPianos);
        productoRepository.save(p99);

        Producto p100 = new Producto();
        p100.setNombre("Korg D1");
        p100.setMarca("Korg");
        p100.setStock(4);
        p100.setPrecio(699.99);
        p100.setDescripcion("Teclado digital profesional con acción RH3 contrapesada.");
        p100.setImagen("/images/products/korg_d1.jpg");
        p100.setSubCategoria(subPianos);
        productoRepository.save(p100);

        Producto p101 = new Producto();
        p101.setNombre("Alesis Recital Pro");
        p101.setMarca("Alesis");
        p101.setStock(6);
        p101.setPrecio(379.99);
        p101.setDescripcion("Piano digital asequible con 88 teclas contrapesadas.");
        p101.setImagen("/images/products/alesis_recital_pro.jpg");
        p101.setSubCategoria(subPianos);
        productoRepository.save(p101);

        Producto p102 = new Producto();
        p102.setNombre("Alesis Prestige");
        p102.setMarca("Alesis");
        p102.setStock(5);
        p102.setPrecio(449.99);
        p102.setDescripcion("Modelo portátil con altavoces integrados y sonido realista.");
        p102.setImagen("/images/products/alesis_prestige.jpg");
        p102.setSubCategoria(subPianos);
        productoRepository.save(p102);

        Producto p103 = new Producto();
        p103.setNombre("Dexibell Vivo H1");
        p103.setMarca("Dexibell");
        p103.setStock(2);
        p103.setPrecio(1399.99);
        p103.setDescripcion("Piano digital profesional con tecnología de modelado avanzado.");
        p103.setImagen("/images/products/dexibell_vivo_h1.jpg");
        p103.setSubCategoria(subPianos);
        productoRepository.save(p103);

        Producto p104 = new Producto();
        p104.setNombre("Nord Piano 5");
        p104.setMarca("Nord");
        p104.setStock(2);
        p104.setPrecio(2499.99);
        p104.setDescripcion("Piano digital profesional para estudio y directo con sonido premium.");
        p104.setImagen("/images/products/nord_piano_5.jpg");
        p104.setSubCategoria(subPianos);
        productoRepository.save(p104);
        
        Producto p105 = new Producto();
        p105.setNombre("Korg Minilogue");
        p105.setMarca("Korg");
        p105.setStock(4);
        p105.setPrecio(549.99);
        p105.setDescripcion("Sintetizador analógico polifónico con gran versatilidad sonora.");
        p105.setImagen("/images/products/korg_minilogue.jpg");
        p105.setSubCategoria(subSintetizadores);
        productoRepository.save(p105);

        Producto p106 = new Producto();
        p106.setNombre("Korg Minilogue XD");
        p106.setMarca("Korg");
        p106.setStock(3);
        p106.setPrecio(649.99);
        p106.setDescripcion("Versión mejorada con motor digital adicional y efectos integrados.");
        p106.setImagen("/images/products/korg_minilogue_xd.jpg");
        p106.setSubCategoria(subSintetizadores);
        productoRepository.save(p106);

        Producto p107 = new Producto();
        p107.setNombre("Korg MS-20 Mini");
        p107.setMarca("Korg");
        p107.setStock(3);
        p107.setPrecio(599.99);
        p107.setDescripcion("Reedición del clásico sintetizador analógico semi-modular.");
        p107.setImagen("/images/products/korg_ms20_mini.jpg");
        p107.setSubCategoria(subSintetizadores);
        productoRepository.save(p107);

        Producto p108 = new Producto();
        p108.setNombre("Roland JUNO-DS61");
        p108.setMarca("Roland");
        p108.setStock(4);
        p108.setPrecio(799.99);
        p108.setDescripcion("Workstation ligero con gran variedad de sonidos y funciones.");
        p108.setImagen("/images/products/roland_juno_ds61.jpg");
        p108.setSubCategoria(subSintetizadores);
        productoRepository.save(p108);

        Producto p109 = new Producto();
        p109.setNombre("Roland FA-06");
        p109.setMarca("Roland");
        p109.setStock(2);
        p109.setPrecio(1099.99);
        p109.setDescripcion("Workstation profesional con potente motor de sonido.");
        p109.setImagen("/images/products/roland_fa06.jpg");
        p109.setSubCategoria(subSintetizadores);
        productoRepository.save(p109);

        Producto p110 = new Producto();
        p110.setNombre("Yamaha MODX6+");
        p110.setMarca("Yamaha");
        p110.setStock(3);
        p110.setPrecio(1199.99);
        p110.setDescripcion("Sintetizador avanzado con motor AWM2 y FM-X.");
        p110.setImagen("/images/products/yamaha_modx6_plus.jpg");
        p110.setSubCategoria(subSintetizadores);
        productoRepository.save(p110);

        Producto p111 = new Producto();
        p111.setNombre("Yamaha Montage M6");
        p111.setMarca("Yamaha");
        p111.setStock(2);
        p111.setPrecio(2899.99);
        p111.setDescripcion("Sintetizador profesional de alto rendimiento para estudio y directo.");
        p111.setImagen("/images/products/yamaha_montage_m6.jpg");
        p111.setSubCategoria(subSintetizadores);
        productoRepository.save(p111);

        Producto p112 = new Producto();
        p112.setNombre("Nord Lead A1");
        p112.setMarca("Nord");
        p112.setStock(2);
        p112.setPrecio(1699.99);
        p112.setDescripcion("Sintetizador virtual analógico con interfaz intuitiva.");
        p112.setImagen("/images/products/nord_lead_a1.jpg");
        p112.setSubCategoria(subSintetizadores);
        productoRepository.save(p112);

        Producto p113 = new Producto();
        p113.setNombre("Arturia MiniBrute 2");
        p113.setMarca("Arturia");
        p113.setStock(4);
        p113.setPrecio(499.99);
        p113.setDescripcion("Sintetizador analógico con arquitectura semi-modular.");
        p113.setImagen("/images/products/arturia_minibrute2.jpg");
        p113.setSubCategoria(subSintetizadores);
        productoRepository.save(p113);

        Producto p114 = new Producto();
        p114.setNombre("Arturia MicroFreak");
        p114.setMarca("Arturia");
        p114.setStock(6);
        p114.setPrecio(349.99);
        p114.setDescripcion("Sintetizador híbrido con múltiples motores digitales.");
        p114.setImagen("/images/products/arturia_microfreak.jpg");
        p114.setSubCategoria(subSintetizadores);
        productoRepository.save(p114);

        Producto p115 = new Producto();
        p115.setNombre("Behringer DeepMind 12");
        p115.setMarca("Behringer");
        p115.setStock(3);
        p115.setPrecio(699.99);
        p115.setDescripcion("Sintetizador analógico polifónico con efectos integrados.");
        p115.setImagen("/images/products/behringer_deepmind12.jpg");
        p115.setSubCategoria(subSintetizadores);
        productoRepository.save(p115);

        Producto p116 = new Producto();
        p116.setNombre("Behringer Model D");
        p116.setMarca("Behringer");
        p116.setStock(5);
        p116.setPrecio(299.99);
        p116.setDescripcion("Módulo analógico inspirado en sintetizadores clásicos.");
        p116.setImagen("/images/products/behringer_model_d.jpg");
        p116.setSubCategoria(subSintetizadores);
        productoRepository.save(p116);

        Producto p117 = new Producto();
        p117.setNombre("Moog Grandmother");
        p117.setMarca("Moog");
        p117.setStock(2);
        p117.setPrecio(999.99);
        p117.setDescripcion("Sintetizador analógico semi-modular con sonido clásico Moog.");
        p117.setImagen("/images/products/moog_grandmother.jpg");
        p117.setSubCategoria(subSintetizadores);
        productoRepository.save(p117);

        Producto p118 = new Producto();
        p118.setNombre("Moog Subsequent 25");
        p118.setMarca("Moog");
        p118.setStock(2);
        p118.setPrecio(1199.99);
        p118.setDescripcion("Sintetizador analógico compacto con sonido potente.");
        p118.setImagen("/images/products/moog_subsequent25.jpg");
        p118.setSubCategoria(subSintetizadores);
        productoRepository.save(p118);

        Producto p119 = new Producto();
        p119.setNombre("Sequential Prophet Rev2");
        p119.setMarca("Sequential");
        p119.setStock(1);
        p119.setPrecio(1999.99);
        p119.setDescripcion("Sintetizador analógico polifónico de nivel profesional.");
        p119.setImagen("/images/products/sequential_prophet_rev2.jpg");
        p119.setSubCategoria(subSintetizadores);
        productoRepository.save(p119);
        
        
        Producto p120 = new Producto();
        p120.setNombre("Hammond XK-5");
        p120.setMarca("Hammond");
        p120.setStock(2);
        p120.setPrecio(2499.99);
        p120.setDescripcion("Órgano digital profesional con auténtico sonido tonewheel.");
        p120.setImagen("/images/products/hammond_xk5.jpg");
        p120.setSubCategoria(subOrganos);
        productoRepository.save(p120);

        Producto p121 = new Producto();
        p121.setNombre("Hammond SKX Pro");
        p121.setMarca("Hammond");
        p121.setStock(2);
        p121.setPrecio(2199.99);
        p121.setDescripcion("Órgano de doble teclado ideal para directo.");
        p121.setImagen("/images/products/hammond_skx_pro.jpg");
        p121.setSubCategoria(subOrganos);
        productoRepository.save(p121);

        Producto p122 = new Producto();
        p122.setNombre("Nord Electro 6D 61");
        p122.setMarca("Nord");
        p122.setStock(3);
        p122.setPrecio(1899.99);
        p122.setDescripcion("Teclado stage con excelentes sonidos de órgano y piano.");
        p122.setImagen("/images/products/nord_electro_6d.jpg");
        p122.setSubCategoria(subOrganos);
        productoRepository.save(p122);

        Producto p123 = new Producto();
        p123.setNombre("Nord C2D");
        p123.setMarca("Nord");
        p123.setStock(1);
        p123.setPrecio(2499.99);
        p123.setDescripcion("Órgano profesional con doble manual y sonido clásico.");
        p123.setImagen("/images/products/nord_c2d.jpg");
        p123.setSubCategoria(subOrganos);
        productoRepository.save(p123);

        Producto p124 = new Producto();
        p124.setNombre("Viscount Legend Solo");
        p124.setMarca("Viscount");
        p124.setStock(2);
        p124.setPrecio(1599.99);
        p124.setDescripcion("Órgano digital compacto con modelado tonewheel realista.");
        p124.setImagen("/images/products/viscount_legend_solo.jpg");
        p124.setSubCategoria(subOrganos);
        productoRepository.save(p124);

        Producto p125 = new Producto();
        p125.setNombre("Viscount Legend Live");
        p125.setMarca("Viscount");
        p125.setStock(2);
        p125.setPrecio(1999.99);
        p125.setDescripcion("Órgano stage con controles físicos y gran versatilidad.");
        p125.setImagen("/images/products/viscount_legend_live.jpg");
        p125.setSubCategoria(subOrganos);
        productoRepository.save(p125);

        Producto p126 = new Producto();
        p126.setNombre("Roland VR-730");
        p126.setMarca("Roland");
        p126.setStock(3);
        p126.setPrecio(1499.99);
        p126.setDescripcion("Combo organ con sonidos vintage y diseño ligero.");
        p126.setImagen("/images/products/roland_vr730.jpg");
        p126.setSubCategoria(subOrganos);
        productoRepository.save(p126);

        Producto p127 = new Producto();
        p127.setNombre("Roland VK-8");
        p127.setMarca("Roland");
        p127.setStock(2);
        p127.setPrecio(1299.99);
        p127.setDescripcion("Órgano digital con modelado clásico y drawbars físicos.");
        p127.setImagen("/images/products/roland_vk8.jpg");
        p127.setSubCategoria(subOrganos);
        productoRepository.save(p127);

        Producto p128 = new Producto();
        p128.setNombre("Yamaha YC61");
        p128.setMarca("Yamaha");
        p128.setStock(3);
        p128.setPrecio(1699.99);
        p128.setDescripcion("Stage keyboard con excelente motor de órgano y efectos.");
        p128.setImagen("/images/products/yamaha_yc61.jpg");
        p128.setSubCategoria(subOrganos);
        productoRepository.save(p128);

        Producto p129 = new Producto();
        p129.setNombre("Yamaha YC73");
        p129.setMarca("Yamaha");
        p129.setStock(2);
        p129.setPrecio(1999.99);
        p129.setDescripcion("Modelo profesional con teclado balanceado y sonidos vintage.");
        p129.setImagen("/images/products/yamaha_yc73.jpg");
        p129.setSubCategoria(subOrganos);
        productoRepository.save(p129);

        Producto p130 = new Producto();
        p130.setNombre("Korg CX-3");
        p130.setMarca("Korg");
        p130.setStock(2);
        p130.setPrecio(1399.99);
        p130.setDescripcion("Órgano digital inspirado en clásicos tonewheel.");
        p130.setImagen("/images/products/korg_cx3.jpg");
        p130.setSubCategoria(subOrganos);
        productoRepository.save(p130);

        Producto p131 = new Producto();
        p131.setNombre("Dexibell Combo J7");
        p131.setMarca("Dexibell");
        p131.setStock(2);
        p131.setPrecio(1799.99);
        p131.setDescripcion("Órgano stage con motor T2L y gran calidad sonora.");
        p131.setImagen("/images/products/dexibell_combo_j7.jpg");
        p131.setSubCategoria(subOrganos);
        productoRepository.save(p131);

        Producto p132 = new Producto();
        p132.setNombre("Johannus Studio P350");
        p132.setMarca("Johannus");
        p132.setStock(1);
        p132.setPrecio(3499.99);
        p132.setDescripcion("Órgano digital litúrgico con múltiples registros.");
        p132.setImagen("/images/products/johannus_p350.jpg");
        p132.setSubCategoria(subOrganos);
        productoRepository.save(p132);

        Producto p133 = new Producto();
        p133.setNombre("Allen Organ GX-235");
        p133.setMarca("Allen");
        p133.setStock(1);
        p133.setPrecio(4999.99);
        p133.setDescripcion("Órgano digital de iglesia con sonido envolvente.");
        p133.setImagen("/images/products/allen_gx235.jpg");
        p133.setSubCategoria(subOrganos);
        productoRepository.save(p133);

        Producto p134 = new Producto();
        p134.setNombre("Hammond M-Solo");
        p134.setMarca("Hammond");
        p134.setStock(3);
        p134.setPrecio(1199.99);
        p134.setDescripcion("Órgano portátil con auténtico carácter vintage.");
        p134.setImagen("/images/products/hammond_m_solo.jpg");
        p134.setSubCategoria(subOrganos);
        productoRepository.save(p134);


        Producto p135 = new Producto();
        p135.setNombre("Pearl Roadshow RS525SC");
        p135.setMarca("Pearl");
        p135.setStock(4);
        p135.setPrecio(599.99);
        p135.setDescripcion("Kit completo ideal para principiantes con gran calidad de construcción.");
        p135.setImagen("/images/products/pearl_roadshow_rs525sc.jpg");
        p135.setSubCategoria(subBateriasAcusticas);
        productoRepository.save(p135);

        Producto p136 = new Producto();
        p136.setNombre("Pearl Export EXX725S");
        p136.setMarca("Pearl");
        p136.setStock(3);
        p136.setPrecio(899.99);
        p136.setDescripcion("Batería versátil con excelente proyección y acabados modernos.");
        p136.setImagen("/images/products/pearl_export_exx725s.jpg");
        p136.setSubCategoria(subBateriasAcusticas);
        productoRepository.save(p136);

        Producto p137 = new Producto();
        p137.setNombre("Tama Imperialstar 5pcs");
        p137.setMarca("Tama");
        p137.setStock(4);
        p137.setPrecio(799.99);
        p137.setDescripcion("Kit robusto con sonido potente ideal para directo.");
        p137.setImagen("/images/products/tama_imperialstar.jpg");
        p137.setSubCategoria(subBateriasAcusticas);
        productoRepository.save(p137);

        Producto p138 = new Producto();
        p138.setNombre("Tama Superstar Classic");
        p138.setMarca("Tama");
        p138.setStock(2);
        p138.setPrecio(1099.99);
        p138.setDescripcion("Batería de arce con gran ataque y sustain.");
        p138.setImagen("/images/products/tama_superstar_classic.jpg");
        p138.setSubCategoria(subBateriasAcusticas);
        productoRepository.save(p138);

        Producto p139 = new Producto();
        p139.setNombre("Yamaha Rydeen RDP2F5");
        p139.setMarca("Yamaha");
        p139.setStock(5);
        p139.setPrecio(649.99);
        p139.setDescripcion("Kit completo con sonido equilibrado y gran estabilidad.");
        p139.setImagen("/images/products/yamaha_rydeen.jpg");
        p139.setSubCategoria(subBateriasAcusticas);
        productoRepository.save(p139);

        Producto p140 = new Producto();
        p140.setNombre("Yamaha Stage Custom Birch");
        p140.setMarca("Yamaha");
        p140.setStock(3);
        p140.setPrecio(999.99);
        p140.setDescripcion("Batería de abedul con sonido definido y profesional.");
        p140.setImagen("/images/products/yamaha_stage_custom_birch.jpg");
        p140.setSubCategoria(subBateriasAcusticas);
        productoRepository.save(p140);

        Producto p141 = new Producto();
        p141.setNombre("Mapex Tornado 5pcs");
        p141.setMarca("Mapex");
        p141.setStock(4);
        p141.setPrecio(499.99);
        p141.setDescripcion("Kit económico ideal para iniciarse en la batería.");
        p141.setImagen("/images/products/mapex_tornado.jpg");
        p141.setSubCategoria(subBateriasAcusticas);
        productoRepository.save(p141);

        Producto p142 = new Producto();
        p142.setNombre("Mapex Armory 6pcs");
        p142.setMarca("Mapex");
        p142.setStock(2);
        p142.setPrecio(1199.99);
        p142.setDescripcion("Batería híbrida con gran pegada y versatilidad.");
        p142.setImagen("/images/products/mapex_armory.jpg");
        p142.setSubCategoria(subBateriasAcusticas);
        productoRepository.save(p142);

        Producto p143 = new Producto();
        p143.setNombre("Gretsch Catalina Club");
        p143.setMarca("Gretsch");
        p143.setStock(3);
        p143.setPrecio(899.99);
        p143.setDescripcion("Kit compacto con sonido cálido y vintage.");
        p143.setImagen("/images/products/gretsch_catalina_club.jpg");
        p143.setSubCategoria(subBateriasAcusticas);
        productoRepository.save(p143);

        Producto p144 = new Producto();
        p144.setNombre("Gretsch Renown Maple");
        p144.setMarca("Gretsch");
        p144.setStock(2);
        p144.setPrecio(1499.99);
        p144.setDescripcion("Batería profesional de arce con gran resonancia.");
        p144.setImagen("/images/products/gretsch_renown_maple.jpg");
        p144.setSubCategoria(subBateriasAcusticas);
        productoRepository.save(p144);

        Producto p145 = new Producto();
        p145.setNombre("Ludwig Accent Drive");
        p145.setMarca("Ludwig");
        p145.setStock(3);
        p145.setPrecio(699.99);
        p145.setDescripcion("Kit completo con sonido clásico y construcción sólida.");
        p145.setImagen("/images/products/ludwig_accent_drive.jpg");
        p145.setSubCategoria(subBateriasAcusticas);
        productoRepository.save(p145);

        Producto p146 = new Producto();
        p146.setNombre("Ludwig Breakbeats by Questlove");
        p146.setMarca("Ludwig");
        p146.setStock(2);
        p146.setPrecio(799.99);
        p146.setDescripcion("Batería compacta ideal para espacios reducidos y directo.");
        p146.setImagen("/images/products/ludwig_breakbeats.jpg");
        p146.setSubCategoria(subBateriasAcusticas);
        productoRepository.save(p146);

        Producto p147 = new Producto();
        p147.setNombre("Sonor AQ2 Stage");
        p147.setMarca("Sonor");
        p147.setStock(2);
        p147.setPrecio(1299.99);
        p147.setDescripcion("Kit de arce con sonido potente y gran definición.");
        p147.setImagen("/images/products/sonor_aq2_stage.jpg");
        p147.setSubCategoria(subBateriasAcusticas);
        productoRepository.save(p147);

        Producto p148 = new Producto();
        p148.setNombre("DW Design Series");
        p148.setMarca("DW");
        p148.setStock(1);
        p148.setPrecio(1799.99);
        p148.setDescripcion("Batería profesional con gran proyección y acabados premium.");
        p148.setImagen("/images/products/dw_design_series.jpg");
        p148.setSubCategoria(subBateriasAcusticas);
        productoRepository.save(p148);

        Producto p149 = new Producto();
        p149.setNombre("PDP Concept Maple");
        p149.setMarca("PDP");
        p149.setStock(3);
        p149.setPrecio(999.99);
        p149.setDescripcion("Kit de arce con sonido equilibrado y moderno.");
        p149.setImagen("/images/products/pdp_concept_maple.jpg");
        p149.setSubCategoria(subBateriasAcusticas);
        productoRepository.save(p149);
        
        Producto p150 = new Producto();
        p150.setNombre("Roland TD-02K");
        p150.setMarca("Roland");
        p150.setStock(5);
        p150.setPrecio(399.99);
        p150.setDescripcion("Batería electrónica compacta ideal para principiantes.");
        p150.setImagen("/images/products/roland_td02k.jpg");
        p150.setSubCategoria(subBateriasElectronicas);
        productoRepository.save(p150);

        Producto p151 = new Producto();
        p151.setNombre("Roland TD-07DMK");
        p151.setMarca("Roland");
        p151.setStock(4);
        p151.setPrecio(799.99);
        p151.setDescripcion("Kit con parches de malla y excelente respuesta dinámica.");
        p151.setImagen("/images/products/roland_td07dmk.jpg");
        p151.setSubCategoria(subBateriasElectronicas);
        productoRepository.save(p151);

        Producto p152 = new Producto();
        p152.setNombre("Roland TD-17KV2");
        p152.setMarca("Roland");
        p152.setStock(3);
        p152.setPrecio(1499.99);
        p152.setDescripcion("Batería electrónica avanzada con sonidos realistas y gran sensibilidad.");
        p152.setImagen("/images/products/roland_td17kv2.jpg");
        p152.setSubCategoria(subBateriasElectronicas);
        productoRepository.save(p152);

        Producto p153 = new Producto();
        p153.setNombre("Roland TD-27KV2");
        p153.setMarca("Roland");
        p153.setStock(2);
        p153.setPrecio(2899.99);
        p153.setDescripcion("Kit profesional con tecnología de modelado avanzada.");
        p153.setImagen("/images/products/roland_td27kv2.jpg");
        p153.setSubCategoria(subBateriasElectronicas);
        productoRepository.save(p153);

        Producto p154 = new Producto();
        p154.setNombre("Yamaha DTX402K");
        p154.setMarca("Yamaha");
        p154.setStock(6);
        p154.setPrecio(449.99);
        p154.setDescripcion("Batería electrónica compacta con múltiples kits integrados.");
        p154.setImagen("/images/products/yamaha_dtx402k.jpg");
        p154.setSubCategoria(subBateriasElectronicas);
        productoRepository.save(p154);

        Producto p155 = new Producto();
        p155.setNombre("Yamaha DTX6K-X");
        p155.setMarca("Yamaha");
        p155.setStock(3);
        p155.setPrecio(1299.99);
        p155.setDescripcion("Kit con parches TCS y sonidos de alta calidad.");
        p155.setImagen("/images/products/yamaha_dtx6kx.jpg");
        p155.setSubCategoria(subBateriasElectronicas);
        productoRepository.save(p155);

        Producto p156 = new Producto();
        p156.setNombre("Alesis Nitro Mesh Kit");
        p156.setMarca("Alesis");
        p156.setStock(7);
        p156.setPrecio(399.99);
        p156.setDescripcion("Batería electrónica con parches de malla y gran relación calidad-precio.");
        p156.setImagen("/images/products/alesis_nitro_mesh.jpg");
        p156.setSubCategoria(subBateriasElectronicas);
        productoRepository.save(p156);

        Producto p157 = new Producto();
        p157.setNombre("Alesis Surge Mesh Kit");
        p157.setMarca("Alesis");
        p157.setStock(5);
        p157.setPrecio(599.99);
        p157.setDescripcion("Kit completo con pads de malla y módulo versátil.");
        p157.setImagen("/images/products/alesis_surge_mesh.jpg");
        p157.setSubCategoria(subBateriasElectronicas);
        productoRepository.save(p157);

        Producto p158 = new Producto();
        p158.setNombre("Alesis Strike Pro SE");
        p158.setMarca("Alesis");
        p158.setStock(2);
        p158.setPrecio(2199.99);
        p158.setDescripcion("Batería electrónica profesional con sonidos detallados.");
        p158.setImagen("/images/products/alesis_strike_pro_se.jpg");
        p158.setSubCategoria(subBateriasElectronicas);
        productoRepository.save(p158);

        Producto p159 = new Producto();
        p159.setNombre("Millenium MPS-850");
        p159.setMarca("Millenium");
        p159.setStock(6);
        p159.setPrecio(699.99);
        p159.setDescripcion("Kit electrónico con pads de malla y múltiples configuraciones.");
        p159.setImagen("/images/products/millenium_mps850.jpg");
        p159.setSubCategoria(subBateriasElectronicas);
        productoRepository.save(p159);

        Producto p160 = new Producto();
        p160.setNombre("Millenium MPS-1000");
        p160.setMarca("Millenium");
        p160.setStock(3);
        p160.setPrecio(1199.99);
        p160.setDescripcion("Batería electrónica avanzada con sensación realista.");
        p160.setImagen("/images/products/millenium_mps1000.jpg");
        p160.setSubCategoria(subBateriasElectronicas);
        productoRepository.save(p160);

        Producto p161 = new Producto();
        p161.setNombre("EFNOTE 3X");
        p161.setMarca("EFNOTE");
        p161.setStock(2);
        p161.setPrecio(1999.99);
        p161.setDescripcion("Kit electrónico moderno con excelente respuesta y diseño minimalista.");
        p161.setImagen("/images/products/efnote_3x.jpg");
        p161.setSubCategoria(subBateriasElectronicas);
        productoRepository.save(p161);

        Producto p162 = new Producto();
        p162.setNombre("EFNOTE 5");
        p162.setMarca("EFNOTE");
        p162.setStock(2);
        p162.setPrecio(2699.99);
        p162.setDescripcion("Batería electrónica profesional con gran realismo sonoro.");
        p162.setImagen("/images/products/efnote_5.jpg");
        p162.setSubCategoria(subBateriasElectronicas);
        productoRepository.save(p162);

        Producto p163 = new Producto();
        p163.setNombre("Donner DED-200");
        p163.setMarca("Donner");
        p163.setStock(6);
        p163.setPrecio(379.99);
        p163.setDescripcion("Kit compacto ideal para práctica en casa.");
        p163.setImagen("/images/products/donner_ded200.jpg");
        p163.setSubCategoria(subBateriasElectronicas);
        productoRepository.save(p163);

        Producto p164 = new Producto();
        p164.setNombre("Roland VAD307");
        p164.setMarca("Roland");
        p164.setStock(1);
        p164.setPrecio(2499.99);
        p164.setDescripcion("Batería electrónica con apariencia acústica y tecnología avanzada.");
        p164.setImagen("/images/products/roland_vad307.jpg");
        p164.setSubCategoria(subBateriasElectronicas);
        productoRepository.save(p164);







        
        
       
         // ======================
        // CESTAS (FK user_id)
        // ======================
        /*Cesta cesta1 = new Cesta();
        cesta1.setUserId(u1.getId());
        cestaRepository.save(cesta1);

        Cesta cesta2 = new Cesta();
        cesta2.setUserId(u2.getId());
        cestaRepository.save(cesta2);*/
        
        // ======================
        // LÍNEAS DE CESTA
        // ======================
        /*LineaCesta l1 = new LineaCesta();
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
        lineaCestaRepository.save(l3);*/

        System.out.println("Categorias creadas: " + categoriaRepository.count());
        System.out.println("Productos creados: " + productoRepository.count());
        //System.out.println("Pedidos creados: " + cestaRepository.count());
        //System.out.println("Lineas de pedido creadas: " + lineaCestaRepository.count());
    }
}
