package com.marketplace.exception;

// Se traduce a HTTP 409 (CONFLICT) Ej: Cédula duplicada, Email ya registrado
public class RecursoDuplicadoException extends RuntimeException {

    // Constructor simple con mensaje claro del conflicto Ej: "El email ya está en uso"
    public RecursoDuplicadoException(String mensaje) {
        super(mensaje);
    }

    //  causa del error si es de base de datos u otra capa
    public RecursoDuplicadoException(String mensaje, Throwable cause) {
        super(mensaje, cause);
    }
}
