package com.oasis_hotel.oasis_hotel.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@Configuration
@OpenAPIDefinition(
    info= @Info(
        title= "Oasis hotel REST API",
        version="1.0",
        description= "Enterprise-grade API for comprehensive management of hotel reservations, rooms, and user accounts",
        contact= @Contact(name="Dylan / Tech Lead Team" , url="https://github.com/Zairex-Code")
    ),
    // Enforces security requirements globally across all swagger endpoints
    security={
        @SecurityRequirement(name="bearerAuth")
    })
    @SecurityScheme(
        name="bearerAuth",
        description="Input your JWT token here to authenticate. The 'Bearer ' prefix is added automatically by swagger",
        scheme="bearer",
        type=SecuritySchemeType.HTTP,
        bearerFormat="JWT",
        in= SecuritySchemeIn.HEADER
    )
public class OpenApiConfig {

}