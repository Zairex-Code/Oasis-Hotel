package com.oasis_hotel.oasis_hotel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.oasis_hotel.oasis_hotel.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserRepository userRepository;
    private final JwtAuthenticationFilter jwtAuthFilter; // (Our "Bouncer")

    /***
     * 
     * The encryption engine used to hash and verify password
     */

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * 
     * Tells Spring Security how to fetch users from our database
     */
    @Bean
    public UserDetailsService userDetailsService(){
        return userName -> userRepository.findByEmail(userName).orElseThrow(()-> new UsernameNotFoundException("User not found into data base"));
    }


    /**
     * 
     * The authentication Provider bridges the database search (UserDetailsService)
     * with the password verifier (PasswordEncoder)
     * NOTE: Spring Boot 4 syntax requires passing the UserDetailsService via constructor
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    /**
     * The Authentication Manager is responsable for processing the login request
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
        return config.getAuthenticationManager();
    }



    // ==========================================
    // THE SECURITY BRAIN (FILTER CHAIN RULES)
    // ==========================================
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
            // We desable CSRF protection because we are using JWT tokens , not session cookies
            .csrf(AbstractHttpConfigurer::disable)
            // 2. CONFIGURE ROUTE PERMISSIONS
            .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/v1/api/auth/**").permitAll()                     //Anyone can attempt to login
                        .requestMatchers(HttpMethod.POST, "/v1/api/users").permitAll()   //Anyone can Register a new account
                        // Private Routes (EVERYTHIG else requires a valid Token)
                        .anyRequest().authenticated())
                                                        // 3. SESSION POLICY (STATELESS)
                                                        // We tell Spring: "Do not save the user's session in memory
                                                        // Every single requests is independent and must bring it own token"
                                                        .sessionManagement(session -> session
                                                            .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                                                                    // 4. POSITION OUR CUSTOM JWT GUARD
                                                                    .authenticationProvider(authenticationProvider())
                                                                    // we tell spring: "Execute my JWT filter BEFORE your default username/password filter"
                                                                    .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
                                                            
        return http.build();
    }
}
