package com.marketplace.repository;

import com.marketplace.entity.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findByCedula(String cedula);

    Optional<Cliente> findByEmail(String email);

    boolean existsByCedula(String cedula);

    boolean existsByEmail(String email);

    Page<Cliente> findByActivoTrue(Pageable pageable);

    Page<Cliente> findByNombreContainingIgnoreCase(String nombre, Pageable pageable);

    Page<Cliente> findByNombreContainingIgnoreCaseAndActivoTrue(String nombre, Pageable pageable);
}
