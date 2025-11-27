package com.valtxcorresponsal.cliente_service.business.domain.services;

import com.valtxcorresponsal.cliente_service.business.api.dtos.CuentaResponseDto;
import com.valtxcorresponsal.cliente_service.business.data.model.entities.AccountEntity;
import com.valtxcorresponsal.cliente_service.business.data.model.entities.UserEntity;

import java.util.List;
import java.util.Optional;

public interface CuentaService {


    // Para traer un Cliente por su id
     List<CuentaResponseDto> getAccountsByClient_NroDocument(String nroDocument);

    UserEntity findUserEntityByUsername(String username);

    Optional<CuentaResponseDto> findByAccountNumber(String accountNumber);
    void actualizarSaldo(String accountNumber, Double nuevoSaldo);

    Iterable<AccountEntity> getAccountsByNroDocument(String nroDocument);

}
