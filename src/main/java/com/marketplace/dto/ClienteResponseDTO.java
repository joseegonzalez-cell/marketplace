package com.marketplace.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record ClienteResponseDTO(

        Long id,
        String nombre,
        String apellido,
        String cedula,
        String email,
        String telefono,
        String direccion,
        String pais,
        String ciudad,
        Boolean activo,

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime fechaRegistro

) {
}
