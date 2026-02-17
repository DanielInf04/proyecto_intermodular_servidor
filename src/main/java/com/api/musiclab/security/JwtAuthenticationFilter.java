package com.api.musiclab.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.filter.OncePerRequestFilter;
import io.jsonwebtoken.*;

import java.io.IOException;
import java.util.Collections;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Override
protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
    String path = request.getServletPath();
    // Excluir login y GET públicos
    if (path.equals("/auth/")) return true;
    
    // Imagenes públicas
    if (path.startsWith("/images/")) return true;
    
    // Solo GET públicos
    if (request.getMethod().equalsIgnoreCase("GET") &&
        (path.startsWith("/api/products")
         || path.startsWith("/api/categories")
         || path.startsWith("/api/subcategories")
         || path.startsWith("/api/categories/"))) {
        return true;
    }
    
    // Comprobación ( (A || B) || (C && isGET) ) return true;
    /*if ((path.startsWith("/api/products") || path.startsWith("/api/categories")) || path.startsWith("/api/subcategories")
            && request.getMethod().equalsIgnoreCase("GET")) {
        return true;
    }*/
    return false;
}

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
       
        if (request.getServletPath().startsWith("/api/products")
            && !request.getMethod().equalsIgnoreCase("GET")) {
          System.out.println("JWT FILTER HIT -> " + request.getMethod() + " " + request.getServletPath()
              + " AUTH=" + request.getHeader("Authorization"));
        }

        // 1. Obtener el token del header
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            try {
                // 2. Validar token y extraer info
                Claims claims = Jwts.parserBuilder()
                        .setSigningKey(JwtUtil.KEY)
                        .build()
                        .parseClaimsJws(token)
                        .getBody();

                String username = claims.getSubject();
                String role = claims.get("role", String.class);

                // 3. Crear objeto de autenticación
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                username,
                                null,
                                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role))
                        );

                // 4. Guardar en contexto de Spring
                SecurityContextHolder.getContext().setAuthentication(authToken);

            } catch (JwtException e) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token inválido");
                return;
            }
        }

        filterChain.doFilter(request, response);
    }
}
