package com.oasis_hotel.oasis_hotel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


import com.oasis_hotel.oasis_hotel.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {
    private final UserRepository userRepository;

    /***
     * The encryption engine used to hash and verify password.
     * We use BCrypt as it is the industry standard.
     */

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    /**
     * Tells Spring Security how to fetch users from our database
     */
    public UserDetailsService userDetailsService(){
        return username -> userRepository.findByEmail(username)
                                        .orElseThrow(()-> new UsernameNotFoundException("User not found in the database"));
    
    }

    /**
     * The aAuthentication Provider bridges de database search (UserDetailsService)
     * With the password verifier (PasswordEncoder).
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        // Modern Spring Boot syntax: Passing UserDetailsService via constructor
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    /**
     *  The Authentication Manager is responsible for processing the login request
     *  that come from our AuthController
     */
    @Bean AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    
}
