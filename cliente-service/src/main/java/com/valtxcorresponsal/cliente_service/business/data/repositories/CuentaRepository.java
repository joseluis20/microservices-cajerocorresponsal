package com.valtxcorresponsal.cliente_service.business.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.valtxcorresponsal.cliente_service.business.data.model.entities.AccountEntity;

import java.util.List;
import java.util.Optional;

public interface CuentaRepository extends JpaRepository<AccountEntity, Long> {

    // Para buscar un cliente por nroDocument
    List<AccountEntity> getAccountsByClient_NroDocument(String nroDocument);

    Optional<AccountEntity> findByAccountNumber(String accountNumber);
}
