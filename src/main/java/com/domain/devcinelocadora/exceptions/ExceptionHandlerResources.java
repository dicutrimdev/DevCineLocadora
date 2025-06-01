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
}
