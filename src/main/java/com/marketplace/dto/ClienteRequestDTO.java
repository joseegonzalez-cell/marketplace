package com.marketplace.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ClienteRequestDTO(

        @NotBlank(message = "El nombre es obligatorio")
        @Size(max = 100, message = "Máximo 100 caracteres")
        String nombre,

        @NotBlank(message = "El apellido es obligatorio")
        @Size(max = 100, message = "Máximo 100 caracteres")
        String apellido,

        @NotBlank(message = "La cédula es obligatoria")
        @Size(max = 20, message = "Máximo 20 caracteres")
        @Pattern(regexp = "\\d+", message = "La cédula solo debe contener números")
        String cedula,

        @NotBlank(message = "El email es obligatorio")
        @Email(message = "Email no válido")
        @Size(max = 150, message = "Máximo 150 caracteres")
        String email,

        @Pattern(regexp = "\\+?[0-9]{7,15}", message = "Teléfono no válido")
        String telefono,

        @Size(max = 255, message = "Máximo 255 caracteres")
        String direccion,

        @Size(max = 100, message = "Máximo 100 caracteres")
        String pais,

        @Size(max = 100, message = "Máximo 100 caracteres")
        String ciudad
) {
}
