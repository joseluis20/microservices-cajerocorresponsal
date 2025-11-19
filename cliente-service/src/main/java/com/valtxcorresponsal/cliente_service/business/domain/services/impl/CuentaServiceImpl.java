package com.valtxcorresponsal.cliente_service.business.domain.services.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.valtxcorresponsal.cliente_service.business.api.dtos.CuentaResponseDto;
import com.valtxcorresponsal.cliente_service.business.data.repositories.CuentaRepository;
import com.valtxcorresponsal.cliente_service.business.domain.mappers.CuentaMapper;
import com.valtxcorresponsal.cliente_service.business.domain.services.CuentaService;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class CuentaServiceImpl implements CuentaService {

  private final CuentaRepository cuentaRepository;
  private final CuentaMapper cuentaMapper;


  @Override
  public List<CuentaResponseDto> getAccountsByClient_NroDocument(String nroDocument) {
    return cuentaRepository
            .getAccountsByClient_NroDocument(nroDocument)
            .stream()
            .map(cuentaMapper::toDto)
            .toList();
  }

    @Override
    public Optional<CuentaResponseDto> findByAccountNumber(String accountNumber) {
        return cuentaRepository
                .findByAccountNumber(accountNumber)
                .map(cuentaMapper::toDto);
    }


    public void actualizarSaldo(String accountNumber, Double nuevoSaldo) {
        var cuenta = cuentaRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada: " + accountNumber));

        cuenta.setBalance(nuevoSaldo);
        cuentaRepository.save(cuenta);
    }




}
