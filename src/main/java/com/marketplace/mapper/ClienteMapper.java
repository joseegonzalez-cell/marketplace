package com.marketplace.mapper;


import com.marketplace.dto.ClienteRequestDTO;
import com.marketplace.dto.ClienteResponseDTO;
import com.marketplace.dto.ClienteUpdateRequestDTO;
import com.marketplace.entity.Cliente;
import org.mapstruct.*;

@Mapper(componentModel = "Spring")
public interface ClienteMapper {

    // Convertir a ResponseDTO
    ClienteResponseDTO toResponse(Cliente cliente);

    // Crear Cliente (POST)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "activo", ignore = true)
    @Mapping(target = "fechaRegistro", ignore = true)
    Cliente toEntity(ClienteRequestDTO requestDTO);


    // Update parcial  (PATCH)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fechaRegistro", ignore = true)
    void updateEntityFromDto(ClienteUpdateRequestDTO dto, @MappingTarget Cliente entity);

}
