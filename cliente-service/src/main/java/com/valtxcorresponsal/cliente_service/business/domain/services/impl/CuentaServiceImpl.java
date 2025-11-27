package com.valtxcorresponsal.cliente_service.business.domain.services.impl;

import com.valtxcorresponsal.cliente_service.business.api.dtos.transaction.UserRequestDto;
import com.valtxcorresponsal.cliente_service.business.api.dtos.transaction.UserResponseDto;
import com.valtxcorresponsal.cliente_service.business.consume.AuthenticationServiceClient;
import com.valtxcorresponsal.cliente_service.business.data.model.entities.AccountEntity;
import com.valtxcorresponsal.cliente_service.business.data.model.entities.UserEntity;
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
  private final AuthenticationServiceClient authenticationServiceClient;


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


    @Override
    public void actualizarSaldo(String accountNumber, Double nuevoSaldo) {
        if (!cuentaRepository.findByAccountNumber(accountNumber).isPresent()) {
            throw new RuntimeException("Cuenta no encontrada: " + accountNumber);
        }

        cuentaRepository.updateBalance(accountNumber, nuevoSaldo);
    }



    @Override
    public UserEntity findUserEntityByUsername(String username) {
        UserRequestDto request = new UserRequestDto();
        request.setUsername(username);

        UserResponseDto response = authenticationServiceClient.getUsuarioByUsername(request);

        UserEntity user = new UserEntity();
        user.setUserName(response.getUserName());
        return user;
    }

    @Override
    public Iterable<AccountEntity> getAccountsByNroDocument(String nroDocument) {
        return this.cuentaRepository.getAccountsByClient_NroDocument(nroDocument);
    }

}
