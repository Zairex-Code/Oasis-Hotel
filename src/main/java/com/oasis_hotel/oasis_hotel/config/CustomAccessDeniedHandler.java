package com.oasis_hotel.oasis_hotel.config;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tools.jackson.databind.ObjectMapper;




@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler{
    /**
     * This method triggers whenever an authenticated user attemps to access
     * a protected HTTP resource they do not have sufficient permissions (Roles) to view, 
     * throwing an AccessDeniedException
     */

    @Override
    public void handle( HttpServletRequest request, 
                        HttpServletResponse response,
                        AccessDeniedException accessDeniedException) throws IOException, ServletException {
        
        // 1. Define the response headers to be JSON
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.FORBIDDEN.value());

        // 2. Build a structured corporate error payload
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now().toString());
        body.put("status", HttpStatus.FORBIDDEN.value());
        body.put("error", "Forbidden");
        body.put("message", "Access Denied: You do not have enough permissions (Roles) to perform this action or access this resource.");
        body.put("path", request.getServletPath());

        // 3. Serialize the Java Map into raw JSON directly into the network response string
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), body);
    }
    
}
