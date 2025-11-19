package com.valtxcorresponsal.cliente_service.business.domain.services.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.valtxcorresponsal.cliente_service.business.api.dtos.ClienteResponseDto;
import com.valtxcorresponsal.cliente_service.business.data.repositories.ClienteRepository;
import com.valtxcorresponsal.cliente_service.business.domain.mappers.ClienteMapper;
import com.valtxcorresponsal.cliente_service.business.domain.services.ClienteService;

import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class ClienteServiceImpl implements ClienteService {

  private final ClienteRepository clienteRepository;
  private final ClienteMapper clienteMapper;


    @Override
    public Optional<ClienteResponseDto> findByNroDocument(String nroDocument) {
        return clienteRepository
                .findByNroDocument(nroDocument)
                .map(clienteMapper::toDto);
    }



}
