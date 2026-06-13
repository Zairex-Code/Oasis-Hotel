package com.oasis_hotel.oasis_hotel.config;

import java.util.Arrays;
import java.util.List;

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

    // 1. We inject the custom JWT FILTER (Our "Bouncer")
    private final JwtAuthenticationFilter jwtAuthFilter;
    // 2. Inject the authentication provider
    private final AuthenticationProvider authenticationProvider;

    // Inject custom security error handlers
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;


    // ==========================================
    // THE SECURITY BRAIN (FILTER CHAIN RULES)
    // ==========================================
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
            .cors(cors -> cors.configurationSource(CorsConfigurationSource())) // Enables CORS bridge for Next.js
            // We disable CSRF protection because we are using JWT tokens , not session cookies
            .csrf(AbstractHttpConfigurer::disable)
            // 2. CONFIGURE ROUTE PERMISSIONS
            .authorizeHttpRequests(auth -> auth
                        // Public Routes (No Token Required)
                        .requestMatchers("/v1/api/auth/**").permitAll()                     //Anyone can attempt to login
                        .requestMatchers(HttpMethod.POST, "/v1/api/users").permitAll()      //Anyone can Register a new account
                        .requestMatchers("/v3/api-docs/**","/swagger-ui/**", "/swagger-ui.html").permitAll() // Allow check the documentation to anyone
                        // Allows unauthenticated guests to search hotels and rooms on the homepage
                        .requestMatchers(HttpMethod.GET, "/v1/api/hotels/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/v1/api/rooms/**").permitAll()
                        // Private Routes (EVERYTHING else requires a valid Token)
                        .anyRequest().authenticated())
                                                        // 3. SESSION POLICY (STATELESS)
                                                        // We tell Spring: "Do not save the user's session in memory
                                                        // Every single requests is independent and must bring it own token"
                                                        .sessionManagement(session -> session
                                                            .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                                                                    // 4. POSITION OUR CUSTOM JWT GUARD
                                                                    // LINK OUR CONFIGURATION TO THE INJECTED PROVIDER AND FILTER
                                                                    .authenticationProvider(authenticationProvider)
                                                                    // we tell spring: "Execute my JWT filter BEFORE your default username/password filter"
                                                                    .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                                                                            // CAPTURE SECURITY EXCEPTIONS WITH PUR CUSTOM JSON RESPONSES
                                                                            .exceptionHandling(exception -> exception
                                                                                    .authenticationEntryPoint(customAuthenticationEntryPoint) // For 401 Unauthorized
                                                                                    .accessDeniedHandler(customAccessDeniedHandler)           // For 403 Forbidden
            );

                                                            
        return http.build();
    }


    private CorsConfigurationSource CorsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        // Allow connection from the future NEXT.JS Frontend
        configuration.setAllowedOrigins(List.of("http://localhost:3000"));

        // Allow standard HTTP methods
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH","DELETE","OPTIONS"));

        // Allow essential headers including JWT Authorization
        configuration.setAllowedHeaders(Arrays.asList(  "Authorization",
                                                        "Content-Type",
                                                        "Accept", 
                                                        "X-Requested-With", 
                                                        "Cache-Control"));

        // Allow credentials (cookies, authorization headers)
        configuration.setAllowCredentials(true);


        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        // Apply this corse rule to all API endpoints
        source.registerCorsConfiguration("/**", configuration);
        return  source;
    }
}
