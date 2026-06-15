package com.oasis_hotel.oasis_hotel.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    // ==========================================
    // 1. DEPENDENCY INJECTION
    // ==========================================
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;

    // ==========================================
    // 2. DYNAMIC ENVIRONMENT PROPERTIES
    // ==========================================
    // Reads allowed origins from the active application.yml file (e.g., dev or prod).
    // This ensures the application is secure in production but flexible in development.
    @Value("${app.cors.allowed-origins}")
    private List<String> allowedOrigins;


    // ==========================================
    // 3. MAIN SECURITY FILTER CHAIN (SPRING BOOT 4 / SECURITY 7)
    // ==========================================
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // Attach the strict CORS configuration defined as a Bean below
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            
            // Disable CSRF because we use stateless JWT tokens instead of session cookies
            .csrf(AbstractHttpConfigurer::disable)
            
            // Route Authorization Rules
            .authorizeHttpRequests(auth -> auth
                // Public Authentication & Registration endpoints
                .requestMatchers("/v1/api/auth/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/v1/api/users").permitAll()
                
                // Public Swagger UI & OpenAPI Documentation
                .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
                
                // Public Domain Exploration (Hotels & Rooms)
                // Matches both exact root paths and their nested sub-paths
                .requestMatchers(HttpMethod.GET, "/v1/api/hotels", "/v1/api/hotels/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/v1/api/rooms", "/v1/api/rooms/**").permitAll()
                
                // All other requests require a valid JWT Token
                .anyRequest().authenticated()
            )
            
            // Session Policy: Make it completely stateless
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            
            // Register Authentication Provider and insert Custom JWT Filter
            .authenticationProvider(authenticationProvider)
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
            
            // Custom Exception Handling for 401 Unauthorized and 403 Forbidden
            .exceptionHandling(exception -> exception
                .authenticationEntryPoint(customAuthenticationEntryPoint)
                .accessDeniedHandler(customAccessDeniedHandler)
            );

        return http.build();
    }


    // ==========================================
    // 4. GLOBAL CORS CONFIGURATION BEAN
    // ==========================================
    // Explicitly defined as a Bean to intercept preflight (OPTIONS) requests early
    // in the Spring Security 7 filter chain.
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        // Inject the allowed origins dynamically from the application YAML files
        configuration.setAllowedOriginPatterns(allowedOrigins);

        // Allow standard REST HTTP methods
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));

        // Allow essential headers for JWT transport and standard REST requests
        configuration.setAllowedHeaders(Arrays.asList(
            "Authorization", 
            "Content-Type", 
            "Accept", 
            "X-Requested-With", 
            "Cache-Control"
        ));

        // Allow credentials (necessary for JWT Authorization headers)
        configuration.setAllowCredentials(true);

        // Apply this configuration to all API endpoints
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        
        return source;
    }
}