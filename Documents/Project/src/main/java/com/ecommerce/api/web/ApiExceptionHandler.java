package com.ecommerce.api.web;

import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.time.Instant;
import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler({IllegalArgumentException.class, IllegalStateException.class})
    ResponseEntity<?> badRequest(RuntimeException ex) {
        return ResponseEntity.badRequest().body(Map.of("timestamp", Instant.now(), "error", ex.getMessage()));
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<?> validation(MethodArgumentNotValidException ex) {
        return ResponseEntity.badRequest().body(Map.of("timestamp", Instant.now(), "error", "Validation failed"));
    }
    @ExceptionHandler(java.util.NoSuchElementException.class)
    ResponseEntity<?> notFound(java.util.NoSuchElementException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("timestamp", Instant.now(), "error", "Resource not found"));
    }
}
