package com.valtxcorresponsal.cliente_service.business.domain.services;

import com.valtxcorresponsal.cliente_service.business.api.dtos.ClienteResponseDto;
import com.valtxcorresponsal.cliente_service.business.data.model.entities.ClientEntity;

import java.util.Optional;

public interface ClienteService {


    // Para traer un Cliente por su id
     Optional<ClienteResponseDto> findByNroDocument(String nroDocument);

      ClientEntity getClientByNroDocument(String nroDocument);

}
