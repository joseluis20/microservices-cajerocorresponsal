package com.valtxcorresponsal.cliente_service.business.domain.services;

import com.valtxcorresponsal.cliente_service.business.api.dtos.CuentaResponseDto;

import java.util.List;
import java.util.Optional;

public interface CuentaService {


    // Para traer un Cliente por su id
     List<CuentaResponseDto> getAccountsByClient_NroDocument(String nroDocument);

    Optional<CuentaResponseDto> findByAccountNumber(String accountNumber);
    void actualizarSaldo(String accountNumber, Double nuevoSaldo);

}
