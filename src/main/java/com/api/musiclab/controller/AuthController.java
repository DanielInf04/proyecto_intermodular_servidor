package com.api.musiclab.controller;

import com.api.musiclab.dto.ApiResponse;
import com.api.musiclab.dto.GoogleLoginRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import java.io.IOException;
import java.security.GeneralSecurityException;
import com.api.musiclab.dto.LoginRequest;
import com.api.musiclab.dto.LoginResponse;
import com.api.musiclab.dto.RegisterRequest;
import com.api.musiclab.entities.Usuario;
import com.api.musiclab.repository.UsuarioRepository;
import com.api.musiclab.security.JwtUtil;
import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder; // ← inyectamos correctamente
    private final GoogleIdTokenVerifier googleIdTokenVerifier;

    @Autowired
    public AuthController(UsuarioRepository usuarioRepository, 
                            PasswordEncoder passwordEncoder,
                            GoogleIdTokenVerifier googleIdTokenVerifier) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.googleIdTokenVerifier = googleIdTokenVerifier;
    }
    
    @PostMapping("/google")
    public ResponseEntity<?> googleLogin(@RequestBody GoogleLoginRequest request)
        throws GeneralSecurityException, IOException {
        
        if (request.getIdToken() == null || request.getIdToken().isBlank()) {
            return ResponseEntity.badRequest().body("idToken requerido");
        }
        
        GoogleIdToken idToken = googleIdTokenVerifier.verify(request.getIdToken());
        if (idToken == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token de Google inválido");
        }
        
        GoogleIdToken.Payload payload = idToken.getPayload();
        
        String email = payload.getEmail();
        String sub = payload.getSubject();
        Boolean emailVerified = (Boolean) payload.get("email_verified");
        
        if (email == null || sub == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token sin email/sub");
        }
        
        // Buscar por googleSub
        Usuario usuario = usuarioRepository.findByGoogleSub(sub).orElse(null);
        
        // Si no existe por googleSub, intenta vincular por email
        if (usuario == null) {
            usuario = usuarioRepository.findByEmail(email).orElse(null);
            
            if (usuario != null) {
                // Vinculamos a una cuenta existente
                usuario.setGoogleSub(sub);
                usuario.setProvider("GOOGLE");
            } else {
                // Creamos un usuario nuevo
                Usuario nuevo = new Usuario();
                nuevo.setEmail(email);
                nuevo.setUsername(email.split("@")[0]);
                nuevo.setRole("USER");
                nuevo.setFechaAlta(LocalDate.now());
                nuevo.setGoogleSub(sub);
                nuevo.setProvider("GOOGLE");
                nuevo.setPassword(null);
                usuario = usuarioRepository.save(nuevo);
            }
            
            usuario = usuarioRepository.save(usuario);
        }
        
        String token = JwtUtil.generateToken(usuario.getUsername(), usuario.getEmail(), usuario.getRole());
        return ResponseEntity.ok(new LoginResponse(token));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {

        String email = request.getEmail().trim().toLowerCase();

        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(email);

        // misma respuesta para "no existe" y "password mal"
        if (usuarioOpt.isEmpty() || !passwordEncoder.matches(request.getPassword(), usuarioOpt.get().getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ApiResponse("Credenciales inválidas"));
        }

        Usuario usuario = usuarioOpt.get();

        String token = JwtUtil.generateToken(
                usuario.getUsername(),
                usuario.getEmail(),
                usuario.getRole()
        );

        return ResponseEntity.ok(new LoginResponse(token));
    }
    
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request) {
        
        if (usuarioRepository.existsByEmail(request.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ApiResponse("El correo  ya está registrado"));
        }

        Usuario usuario = new Usuario();
        usuario.setUsername(request.getUsername().trim());
        usuario.setEmail(request.getEmail().trim().toLowerCase());
        usuario.setPassword(passwordEncoder.encode(request.getPassword()));

        usuario.setRole("USER");
        usuario.setFechaAlta(LocalDate.now());

        usuarioRepository.save(usuario);

        // No devuelvas el usuario entero (incluye password)
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse("Usuario creado correctamente"));
    }
    
    /*@PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Usuario usuario) {

        // Verificar si ya existe el username
        if (usuarioRepository.findByUsername(usuario.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("El usuario ya existe");
        }

        // Encriptar contraseña
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));

        // Asignar rol por defecto
        usuario.setRole("USER");
        usuario.setFechaAlta(LocalDate.now());
        Usuario usuarioGuardado = usuarioRepository.save(usuario);

        return ResponseEntity.ok(usuarioGuardado);
    }*/

}
