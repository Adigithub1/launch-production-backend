package com.launch.studentservice.exception;

import java.time.Instant;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(
            IllegalArgumentException.class
    )
    public ResponseEntity<Map<String, Object>>
            handleNotFound(
                    IllegalArgumentException ex) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(
                    Map.of(
                        "message",
                        ex.getMessage(),

                        "timestamp",
                        Instant.now().toString()
                    )
                );
    }

    @ExceptionHandler(
            IllegalStateException.class
    )
    public ResponseEntity<Map<String, Object>>
            handleConflict(
                    IllegalStateException ex) {

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(
                    Map.of(
                        "message",
                        ex.getMessage(),

                        "timestamp",
                        Instant.now().toString()
                    )
                );
    }
}