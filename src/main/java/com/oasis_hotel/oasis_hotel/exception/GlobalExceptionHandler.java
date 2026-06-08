package com.oasis_hotel.oasis_hotel.exception;

import java.net.URI;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ProblemDetail handleResourceNotFoundException(ResourceNotFoundException e){

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
        problemDetail.setTitle("Resource Not Found");
        // this is to create a link with the error url and how to solve it, if you want you can skip it is not mandatory 
        problemDetail.setType(URI.create("https://api.oasishotels.com/errors/not-found")); 
        problemDetail.setProperty("timestamp", Instant.now());
        return problemDetail;
    }

    @ExceptionHandler({IllegalArgumentException.class, IllegalStateException.class})
    public ProblemDetail handleBusinessRuleException(RuntimeException e){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
        problemDetail.setTitle("Business Rule violation");
        problemDetail.setType(URI.create("https://api.oasishotels.com/errors/business-rule-violation"));
        problemDetail.setProperty("timestamp", Instant.now());
        return problemDetail;

    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
        MethodArgumentNotValidException e,
        HttpHeaders headers,
        HttpStatusCode status,
        WebRequest request
    ){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "This request contains validations errors, check fields");
        problemDetail.setTitle("Validation Error");
        problemDetail.setType(URI.create("https://api.oasishotels.com/errors/validation"));

        // Extract all failed fields 
        Map<String, String> validationErrors = new HashMap<>();
        for(FieldError fieldError : e.getBindingResult().getFieldErrors()){
            validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        problemDetail.setProperty("errors", validationErrors);
        problemDetail.setProperty("timestamp", Instant.now());

        return new ResponseEntity<>(problemDetail,HttpStatus.BAD_REQUEST);
    }






}
