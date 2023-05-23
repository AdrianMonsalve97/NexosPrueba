package com.prueba.amonsalve.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiError> handleApiException(ApiException ex) {
        ApiError error = new ApiError(ex.getStatus(), ex.getMessage());
        return ResponseEntity.status(ex.getStatus()).body(error);
    }


    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        // Manejar la excepción y devolver una respuesta adecuada
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error en el servidor");
    }
}
