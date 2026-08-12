package com.marketplace.service;

import com.marketplace.dto.ClienteRequestDTO;
import com.marketplace.dto.ClienteResponseDTO;
import com.marketplace.dto.ClienteUpdateRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClienteService {

    // Crear cliente
    ClienteResponseDTO crear(ClienteRequestDTO requestDTO);

    // Actualizar cliente
    ClienteResponseDTO actualizar(
            Long id,
            ClienteUpdateRequestDTO requestDTO
    );

    // Obtener cliente por ID
    ClienteResponseDTO obtenerPorId(Long id);

    // Listar todos los clientes
    Page<ClienteResponseDTO> listarTodos(Pageable pageable);

    // Listar clientes activos
    Page<ClienteResponseDTO> listarActivos(Pageable pageable);

    // Eliminación lógica
    void eliminar(Long id);

    // Obtener cliente por email
    ClienteResponseDTO obtenerPorEmail(String email);

    // Buscar clientes por nombre
    Page<ClienteResponseDTO> buscarPorNombre(
            String nombre,
            Pageable pageable
    );
}