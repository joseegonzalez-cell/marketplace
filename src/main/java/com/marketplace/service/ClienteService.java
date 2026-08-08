package com.marketplace.service;

import com.marketplace.dto.ClienteRequestDTO;
import com.marketplace.dto.ClienteResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClienteService {

    // Crear Cliente
    ClienteResponseDTO crear(ClienteRequestDTO requestDTO);

    // Actualizar cliente
    ClienteResponseDTO actualizar(Long id, ClienteResponseDTO responseDTO);

    // Obtener Cliente por ID
    ClienteResponseDTO obtnerPorId(Long id);

    //Listar todos los Clientes
    Page<ClienteResponseDTO> listarTodos(Pageable pageable);

    // Listar Clientes activos
    Page<ClienteResponseDTO> listarActivos(Pageable pageable);

    // Eliminar cliente
    void eliminar(Long id);

    // Obtener Cliente por Email
    ClienteResponseDTO obtenerPorEmail(String email);

    // Buscar Cliente por nombre
    Page<ClienteResponseDTO> buscarPorNombre(String nombre, Pageable pageable);
}
