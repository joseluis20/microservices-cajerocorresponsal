package com.valtxcorresponsal.cliente_service.business.domain.services;

import com.valtxcorresponsal.cliente_service.business.data.model.entities.WalletEntity;

import java.util.Optional;

public interface WalletService {

    // Para buscar una billetera indicando el propietario de la misma   dddddddddddddddddd
    Optional<WalletEntity> getWalletByUser(Long userId);

}
