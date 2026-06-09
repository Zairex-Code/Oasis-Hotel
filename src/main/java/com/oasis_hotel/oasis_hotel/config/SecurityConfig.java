package com.oasis_hotel.oasis_hotel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    // 1. We inject the custom JWT FILTER (Our "Bouncer")
    private final JwtAuthenticationFilter jwtAuthFilter;
    // 2. Inject the authentication provider
    private final AuthenticationProvider authenticationProvider;


    // ==========================================
    // THE SECURITY BRAIN (FILTER CHAIN RULES)
    // ==========================================
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
            // We disable CSRF protection because we are using JWT tokens , not session cookies
            .csrf(AbstractHttpConfigurer::disable)
            // 2. CONFIGURE ROUTE PERMISSIONS
            .authorizeHttpRequests(auth -> auth
                        // Public Routes (No Token Required)
                        .requestMatchers("/v1/api/auth/**").permitAll()                     //Anyone can attempt to login
                        .requestMatchers(HttpMethod.POST, "/v1/api/users").permitAll()      //Anyone can Register a new account
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
                                                                    .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
                                                            
        return http.build();
    }
}
