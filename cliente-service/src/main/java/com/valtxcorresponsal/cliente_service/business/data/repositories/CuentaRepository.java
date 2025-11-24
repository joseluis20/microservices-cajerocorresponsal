package com.valtxcorresponsal.cliente_service.business.data.repositories;

import feign.Param;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.valtxcorresponsal.cliente_service.business.data.model.entities.AccountEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CuentaRepository extends JpaRepository<AccountEntity, Long> {

    // Para buscar un cliente por nroDocument
    List<AccountEntity> getAccountsByClient_NroDocument(String nroDocument);

    Optional<AccountEntity> findByAccountNumber(String accountNumber);

    // SOLO ACTUALIZA EL SALDO,
    @Modifying
    @Transactional
    @Query("UPDATE AccountEntity a SET a.balance = :saldo WHERE a.accountNumber = :numero")
    void updateBalance(@Param("numero") String numero, @Param("saldo") Double saldo);
}
