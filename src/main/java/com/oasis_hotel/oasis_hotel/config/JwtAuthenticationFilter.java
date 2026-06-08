package com.oasis_hotel.oasis_hotel.config;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
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

public class JwtAuthenticationFilter extends OncePerRequestFilter{

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    
    /**
     * This method intercepts EVERY single HTTP request that hits our API
     * It checks for the JWT Token, validates  it, and sets the user context
    */
    
    @Override
    protected void doFilterInternal(
                            @NonNull HttpServletRequest request, 
                            @NonNull HttpServletResponse response, 
                            @NonNull FilterChain filterChain) throws ServletException, IOException {

        
        // 1. Extract the Authorization header
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;

        
        // 2. Check id the header exists and stars with "Bearer "
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            // If there's not token, we just pass the request to the next filter
            // Spring Security will eventually block it if the route requires authentication
            filterChain.doFilter(request, response);
            return;
        }

        // 3. Extract the JWT token (Removing the "Bearer " prefix)
        jwt = authHeader.substring(7);

        // 4. Extract the username (email) from the token
        userEmail = jwtService.extractUsername(jwt);

        // 5. If we have an email and the user is not authenticated yet in the current context
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            // Fetch user details from the database
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);

            // 6. Validate if the token mathematically
            if (jwtService.isTokenValid(jwt, userDetails)) {

                // 7. Create the authentication object expected by spring security
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                // add extra details about the request (example , IP address , session ID)
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // 8. Update the security context holder
                // This tells spring: "This user is fully authenticated and allowed to proceed"
                SecurityContextHolder.getContext().setAuthentication(authToken);

            }
            
        }

        // 9. continue the execution of the request (Pass it to the controller)
        filterChain.doFilter(request, response);
    }

    



    
}
