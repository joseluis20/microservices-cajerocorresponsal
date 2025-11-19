package com.valtxcorresponsal.cliente_service.business.domain.services;

import com.valtxcorresponsal.cliente_service.business.api.dtos.ClienteResponseDto;

import java.util.Optional;

public interface ClienteService {


    // Para traer un Cliente por su id
     Optional<ClienteResponseDto> findByNroDocument(String nroDocument);



}
