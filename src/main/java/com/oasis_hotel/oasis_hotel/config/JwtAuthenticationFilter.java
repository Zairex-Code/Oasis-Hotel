package com.oasis_hotel.oasis_hotel.config;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.oasis_hotel.oasis_hotel.service.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    
    /**
     * This method intercepts EVERY single HTTP request that hits our API.
     * It checks for the JWT Token, validates it, and sets the user context.
     */
    @Override
    protected void doFilterInternal(
                            @NonNull HttpServletRequest request, 
                            @NonNull HttpServletResponse response, 
                            @NonNull FilterChain filterChain) throws ServletException, IOException {

        // 🚀 MASTER CORS SOLUTION (Pre-flight OPTIONS request)
        // When the browser asks (OPTIONS) if it has permission to execute a PUT/PATCH/DELETE,
        // we intercept the request and grant the green light with a 200 OK immediately.
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000"); // Allow frontend origin
            response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, PATCH, DELETE, OPTIONS");
            response.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type");
            response.setStatus(HttpServletResponse.SC_OK);
            return; // We halt execution here. We do NOT proceed to validate tokens for OPTIONS requests!
        }

        // 1. Extract the Authorization header
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;
        
        // 2. Check if the header exists and starts with "Bearer "
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            // If there's no token, we just pass the request to the next filter
            filterChain.doFilter(request, response);
            return;
        }
        
        try {
            // 3. Extract the JWT token (Removing the "Bearer " prefix)
            jwt = authHeader.substring(7);
    
            // 4. Extract the username (email) from the token
            userEmail = jwtService.extractUsername(jwt);
    
            // 5. If we have an email and the user is not authenticated yet
            if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
    
                // Fetch user details from the database
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
    
                // 6. Validate the token mathematically
                if (jwtService.isTokenValid(jwt, userDetails)) {
    
                    // 7. Create the authentication object expected by spring security
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    
                    // Add extra details about the request
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
    
                    // 8. Update the security context holder
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        } catch(Exception e) {
            // PRODUCTION GUARD-RAIL: If the token is malformed, expired or fake, we catch the exception.
            // Spring Security will automatically delegate the failure to CustomAuthenticationEntryPoint.
        }

        // 9. Continue the execution of the request (Pass it to the controller)
        filterChain.doFilter(request, response);
    }
}