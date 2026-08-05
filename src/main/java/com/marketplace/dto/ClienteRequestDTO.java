package com.marketplace.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ClienteRequestDTO(

        @NotBlank(message = "El nombre es obligatorio")
        @Size(max = 100, message = "El nombre debe tener máximo 100 caracteres")
        String nombre,

        @NotBlank(message = "El apellido es obligatorio")
        @Size(max = 100, message = "El apellido debe tener máximo 100 caracteres")
        String apellido,

        @NotBlank(message = "La cédula es obligatoria")
        @Size(max = 20, message = "La cédula debe tener máximo 20 caracteres")
        @Pattern(regexp = "\\d+", message = "La cédula solo debe contener números")
        String cedula,

        @NotBlank(message = "El email es obligatorio")
        @Email(message = "Email no válido, debe usar un email que este activo")
        @Size(max = 150, message = "El email debe tener máximo 150 caracteres")
        String email,

        @Pattern(regexp = "\\+?[0-9]{7,15}", message = "Teléfono no válido")
        String telefono,

        @Size(max = 255, message = "La dirección debe tener máximo 255 caracteres")
        String direccion,

        @Size(max = 100, message = "El país debe tener máximo 100 caracteres")
        String pais,

        @Size(max = 100, message = "La ciudad debe tener máximo 100 caracteres")
        String ciudad

) { }
