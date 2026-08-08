package com.marketplace.exception.handler;

import com.marketplace.exception.RecursoDuplicadoException;
import com.marketplace.exception.RecursoNoEncontradoException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j // Habilita logging automático
@RestControllerAdvice
public class GlobalExceptionHandler {

    // 404 - RECURSO NO ENCONTRADO
    @ExceptionHandler(RecursoNoEncontradoException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(
            RecursoNoEncontradoException ex,
            HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(
                        HttpStatus.NOT_FOUND.value(), // Código HTTP
                        "NOT_FOUND",                  // tipo de error
                        ex.getMessage(),              // mensaje del service
                        request.getRequestURI(),      // endpoint que falló
                        LocalDateTime.now()           // timestamp
                ));
    }

    // 409 - RECURSO DUPLICADO
    @ExceptionHandler(RecursoDuplicadoException.class)
    public ResponseEntity<ErrorResponse> handleDuplicate(
            RecursoDuplicadoException ex,
            HttpServletRequest request) {
        return  ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ErrorResponse(
                        HttpStatus.CONFLICT.value(),
                        "CONFLICT",
                        ex.getMessage(),
                        request.getRequestURI(),
                        LocalDateTime.now()
                ));
    }

    // 400 - ERRORES DE VALIDACIÓN (@Valid)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> handleValidation(
            MethodArgumentNotValidException ex,
            HttpServletRequest request) {

        Map<String, String> errores = new HashMap<>();

        // Recorrer cada validación y convertir en campo: mensaje
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errores.put(error.getField(), error.getDefaultMessage());
        }

        return  ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ValidationErrorResponse(
                        HttpStatus.BAD_REQUEST.value(),
                        "BAD_REQUEST",
                        "Error de validación en los campos",
                        request.getRequestURI(),
                        LocalDateTime.now(),
                        errores  // errores detalladosa por campo
                ));

    }

    // 409 - ERRORES DE BASES DE DATOS
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrity(
            DataIntegrityViolationException ex,
            HttpServletRequest request) {

        // loguear el error
        log.error("Error de integridad de datos: ", ex);

        return  ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ErrorResponse(
                        HttpStatus.CONFLICT.value(),
                        "CONFLICT",
                        "Violación de integridad de datos (posible duplicado o restricción)",
                        request.getRequestURI(),
                        LocalDateTime.now()
                ));

    }

    // 500 - ERROR GÉNERICO (FALLBACK)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneric(
            Exception ex,
            HttpServletRequest request) {

        // log de errores inesperados
        log.error("Error interno del servidor: ", ex);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse(
                        HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        "INTERNAL_SERVER_ERROR",
                        "Ocurrió un error inesperado",
                        request.getRequestURI(),
                        LocalDateTime.now()
                ));
    }

    // RESPUESTA ESTÁNDAR DEL ERROR
    public record ErrorResponse(
            int status,
            String error,
            String mensaje,
            String path,
            LocalDateTime timestamp
    ){}

    // RESPUESTA PARA VALIDACIONES
    public record ValidationErrorResponse(
            int status,
            String error,
            String mensaje,
            String path,
            LocalDateTime timestamp,
            Map<String, String> errores // errores campo -> mensaje
    ) {}
}
