package com.marketplace.service.impl;

import com.marketplace.dto.ClienteRequestDTO;
import com.marketplace.dto.ClienteResponseDTO;
import com.marketplace.dto.ClienteUpdateRequestDTO;
import com.marketplace.entity.Cliente;
import com.marketplace.exception.RecursoDuplicadoException;
import com.marketplace.exception.RecursoNoEncontradoException;
import com.marketplace.mapper.ClienteMapper;
import com.marketplace.repository.ClienteRepository;
import com.marketplace.service.ClienteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    // Crear cliente
    @Override
    public ClienteResponseDTO crear(ClienteRequestDTO requestDTO) {

        log.info("Creando cliente con cédula: {}", requestDTO.cedula());

        Cliente cliente = clienteMapper.toEntity(requestDTO);
        cliente.setActivo(true);

        try {
            Cliente guardado = clienteRepository.save(cliente);

            return clienteMapper.toResponse(guardado);

        } catch (DataIntegrityViolationException e) {

            log.warn(
                    "Conflicto al crear cliente. Email: {}",
                    requestDTO.email()
            );

            throw new RecursoDuplicadoException(
                    "Cédula o email ya existen",
                    e
            );
        }
    }

    // Actualizar cliente
    @Override
    public ClienteResponseDTO actualizar(
            Long id,
            ClienteUpdateRequestDTO requestDTO) {

        Cliente cliente = buscarClientePorId(id);

        // Valida campos únicos
        validarDuplicados(cliente, requestDTO);

        // Actualiza campos no nulos
        clienteMapper.updateEntityFromDto(requestDTO, cliente);

        return clienteMapper.toResponse(cliente);
    }

    // Obtener cliente por ID
    @Override
    @Transactional(readOnly = true)
    public ClienteResponseDTO obtenerPorId(Long id) {

        Cliente cliente = buscarClientePorId(id);

        return clienteMapper.toResponse(cliente);
    }

    // Listar todos los clientes
    @Override
    @Transactional(readOnly = true)
    public Page<ClienteResponseDTO> listarTodos(Pageable pageable) {

        return clienteRepository.findAll(pageable)
                .map(clienteMapper::toResponse);
    }

    // Listar clientes activos
    @Override
    @Transactional(readOnly = true)
    public Page<ClienteResponseDTO> listarActivos(Pageable pageable) {

        return clienteRepository.findByActivoTrue(pageable)
                .map(clienteMapper::toResponse);
    }

    // Eliminación lógica
    @Override
    public void eliminar(Long id) {

        Cliente cliente = buscarClientePorId(id);

        cliente.setActivo(false);

        log.info("Cliente desactivado con ID: {}", id);
    }

    // Obtener cliente por email
    @Override
    @Transactional(readOnly = true)
    public ClienteResponseDTO obtenerPorEmail(String email) {

        Cliente cliente = clienteRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RecursoNoEncontradoException(
                                "Cliente no encontrado con email: " + email
                        )
                );

        return clienteMapper.toResponse(cliente);
    }

    // Buscar clientes por nombre
    @Override
    @Transactional(readOnly = true)
    public Page<ClienteResponseDTO> buscarPorNombre(
            String nombre,
            Pageable pageable) {

        return clienteRepository
                .findByNombreContainingIgnoreCase(nombre, pageable)
                .map(clienteMapper::toResponse);
    }

    // Buscar cliente por ID
    private Cliente buscarClientePorId(Long id) {

        return clienteRepository.findById(id)
                .orElseThrow(() ->
                        new RecursoNoEncontradoException(
                                "Cliente no encontrado con id: " + id
                        )
                );
    }

    // Validar duplicados
    private void validarDuplicados(
            Cliente cliente,
            ClienteUpdateRequestDTO requestDTO) {

        // Validar cédula
        if (requestDTO.cedula() != null
                && !requestDTO.cedula().equals(cliente.getCedula())
                && clienteRepository.existsByCedula(requestDTO.cedula())) {

            throw new RecursoDuplicadoException(
                    "Ya existe un cliente con cédula: "
                            + requestDTO.cedula()
            );
        }

        // Validar email
        if (requestDTO.email() != null
                && !requestDTO.email().equals(cliente.getEmail())
                && clienteRepository.existsByEmail(requestDTO.email())) {

            throw new RecursoDuplicadoException(
                    "Ya existe un cliente con email: "
                            + requestDTO.email()
            );
        }
    }
}