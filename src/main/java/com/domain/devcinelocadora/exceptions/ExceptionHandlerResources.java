package com.domain.devcinelocadora.exceptions;

import org.springframework.http.HttpStatus;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.domain.devcinelocadora.dto.error.CustomErrorAttributes;
import org.springframework.web.bind.MethodArgumentNotValidException;
import com.domain.devcinelocadora.dto.error.CustomErrorAttributesValidation;

import java.time.Instant;

@ControllerAdvice
public class ExceptionHandlerResources {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomErrorAttributes> methodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        var customErrorAttributesValidation = new CustomErrorAttributesValidation(
                Instant.now(),
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                "Invalid data",
                request.getRequestURI()
        );
        for (FieldError f : ex.getBindingResult().getFieldErrors()) {
            customErrorAttributesValidation.addErrorMessage(f.getField(), f.getDefaultMessage());
        }
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(customErrorAttributesValidation);
    }

    @ExceptionHandler({
            AluguelNotFoundException.class,
            ClienteNotFoundException.class,
            FilmeNotFoundException.class
    })
    public ResponseEntity<CustomErrorAttributes> handleNotFoundExceptions(RuntimeException ex, HttpServletRequest request) {
        var error = new CustomErrorAttributes(
                Instant.now(),
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(EstoqueInsuficienteException.class)
    public ResponseEntity<CustomErrorAttributes> handleEstoqueInsuficienteException(EstoqueInsuficienteException ex, HttpServletRequest request) {
        var error = new CustomErrorAttributes(
                Instant.now(),
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomErrorAttributes> handleGenericException(Exception ex, HttpServletRequest request) {
        var error = new CustomErrorAttributes(
                Instant.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Unexpected error: " + ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
