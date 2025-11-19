package com.valtxcorresponsal.cliente_service.business.domain.mappers;

import com.valtxcorresponsal.cliente_service.business.api.dtos.ClienteRequestDto;
import com.valtxcorresponsal.cliente_service.business.api.dtos.ClienteResponseDto;
import com.valtxcorresponsal.cliente_service.business.data.model.entities.ClientEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    @Mapping(target = "tipDocCli", source = "typeDocument.tipoDocument")
    ClienteResponseDto toDto(ClientEntity clientEntity);

    @Mapping(target = "idClient", ignore = true)
    ClientEntity toEntity(ClienteRequestDto clienteDto);

    @Mapping(target = "idClient", ignore = true)
    void updateEntityFromDto(ClienteRequestDto clienteDto,
                             @MappingTarget ClientEntity clienteEntity);
}
