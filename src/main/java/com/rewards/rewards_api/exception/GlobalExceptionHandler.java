package com.rewards.rewards_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Global exception handler for application.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handle invalid arguments.
     *
     * @param ex exception
     * @return bad request response
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(
            IllegalArgumentException ex) {

        return new ResponseEntity<>(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST
        );
    }

    /**
     * Handle resource not found exception.
     *
     * @param ex exception
     * @return not found response
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(
            ResourceNotFoundException ex) {

        return new ResponseEntity<>(
                ex.getMessage(),
                HttpStatus.NOT_FOUND
        );
    }

    /**
     * Handle generic exceptions.
     *
     * @param ex exception
     * @return internal server error response
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(
            Exception ex) {

        return new ResponseEntity<>(
                "Something went wrong",
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}