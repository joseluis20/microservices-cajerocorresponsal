package com.valtxcorresponsal.cliente_service.business.domain.services.impl;



import com.valtxcorresponsal.cliente_service.business.data.model.entities.WalletEntity;
import com.valtxcorresponsal.cliente_service.business.data.repositories.WalletRepository;
import com.valtxcorresponsal.cliente_service.business.domain.services.WalletService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;

    @Override
    public Optional<WalletEntity> getWalletByUser(Long userId) {

        return this.walletRepository.findByUser_IdPerfil(userId);
    }


}
