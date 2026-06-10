package com.oasis_hotel.oasis_hotel.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.net.URI;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

/**
 * Global Exception Handler interceptor.
 * Core Mold Practice: Implements RFC 7807 standard (ProblemDetail) for modern Spring Boot 4 API error responses.
 * Perfectly structured to serve dynamic error feedback directly to Next.js forms.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Catches payload validation errors (triggered by @Valid annotations in controllers).
     * Formats bean validation failures into a standardized RFC 7807 ProblemDetail object.
     * * @param ex The validation exception containing metadata about failed fields.
     * @return A unified ProblemDetail structure containing a nested map of field-specific errors.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ProblemDetail> handleValidationExceptions(MethodArgumentNotValidException ex) {
        // 1. Initialize the official Spring ProblemDetail wrapper with a 400 Bad Request
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST, 
                "The request payload contains validation errors. Please inspect the 'errors' fields."
        );
        
        problemDetail.setTitle("Validation Error");
        problemDetail.setType(URI.create("https://api.oasishotels.com/errors/validation"));

        // 2. Extract all failed fields cleanly using streams (Modern Functional Java)
        Map<String, String> validationErrors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            validationErrors.put(fieldName, errorMessage);
        });

        // 3. Inject dynamic extensions for custom frontend rendering
        problemDetail.setProperty("errors", validationErrors);
        problemDetail.setProperty("timestamp", Instant.now());

        return new ResponseEntity<>(problemDetail, HttpStatus.BAD_REQUEST);
    }

    /**
     * Catches 'Resource Not Found' exceptions (e.g., missing hotels, users, or reservations).
     * Maps the business logic domain error straight into a 404 ProblemDetail.
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ProblemDetail> handleResourceNotFoundException(ResourceNotFoundException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        
        problemDetail.setTitle("Resource Not Found");
        problemDetail.setType(URI.create("https://api.oasishotels.com/errors/not-found"));
        problemDetail.setProperty("timestamp", Instant.now());

        return new ResponseEntity<>(problemDetail, HttpStatus.NOT_FOUND);
    }

    /**
     * Generic catch-all fallback for any unexpected system exceptions.
     * Prevents internal technology leakages (stacktraces) from reaching the client layer.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ProblemDetail> handleGlobalException(Exception ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.INTERNAL_SERVER_ERROR, 
                "An unexpected internal server error occurred on our infrastructure."
        );
        
        problemDetail.setTitle("Internal Server Error");
        problemDetail.setType(URI.create("https://api.oasishotels.com/errors/internal-server-error"));
        problemDetail.setProperty("timestamp", Instant.now());

        // Technical note: Good practice logs the original exception locally for tracing, without exposure
        // logger.error("Unhandled exception caught: ", ex);

        return new ResponseEntity<>(problemDetail, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}