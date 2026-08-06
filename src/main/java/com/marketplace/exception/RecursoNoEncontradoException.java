package com.marketplace.exception;

// Se traduce a HTTP 404 en el GlobalExceptionHandler
public class RecursoNoEncontradoException extends RuntimeException {

    // Constructor simple personalizado Ej: "Cliente no encontrado co ID: 10"
    public RecursoNoEncontradoException(String mensaje) {
        super(mensaje);
    }

    // Constructor que permite encadenar la causa original del error
    public RecursoNoEncontradoException(String mensaje, Throwable cause) {
        super(mensaje, cause);
    }
}
