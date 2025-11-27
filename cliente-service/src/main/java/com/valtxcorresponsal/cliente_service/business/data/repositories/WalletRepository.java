package com.valtxcorresponsal.cliente_service.business.data.repositories;

import com.valtxcorresponsal.cliente_service.business.data.model.entities.AccountEntity;
import com.valtxcorresponsal.cliente_service.business.data.model.entities.WalletEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WalletRepository extends JpaRepository<WalletEntity, Long> {

     // Para buscar una billetera indicando el propietario de la misma
    Optional<WalletEntity> findByUser_IdPerfil(Long userId);


}
