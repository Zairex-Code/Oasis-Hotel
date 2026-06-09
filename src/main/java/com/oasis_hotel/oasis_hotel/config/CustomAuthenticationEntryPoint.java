package com.oasis_hotel.oasis_hotel.config;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tools.jackson.databind.ObjectMapper;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    /**
     * This method triggers whenever an unauthenticated user attempts to access
     * a secured HTTP resource and a AuthenticationException is thrown.
     */

    @Override
    public void  commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException , ServletException{
        // 1. Define the response headers to be JSON
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.UNAUTHORIZED.value());

        // 2. Build a structured corporate error payload
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now().toString());
        body.put("status", HttpStatus.UNAUTHORIZED.value());
        body.put("error", "Unauthorized");
        body.put("message", "Authentication required: You need to log in to access this resource or your token is invalid/expired.");
        body.put("path", request.getServletPath());


        // 3. Serialize the java map into raw JSON directly into the network response stream
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), body);

    }
    
}
