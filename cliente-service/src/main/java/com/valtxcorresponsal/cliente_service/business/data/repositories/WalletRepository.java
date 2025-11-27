package com.valtx.corresponsal_backend.domain.port;

import com.valtx.corresponsal_backend.domain.model.Wallet;

import java.util.Optional;

public interface WalletRepository {

    // Para mostrar todas las billeteras
    Iterable<Wallet> getWallets();
    // Para mostrar una billetera buscandola por id
    Optional<Wallet> getWallet (Long id);
    // Para guardar una billetera
    Wallet saveWallet (Wallet wallet);
    // Para eliminar una billetera indicando el id de la misma
    void deleteWalletById(Long id);

    // Para guardar una billetera indicando quien sera propietario de la misma
    Wallet saveWalletByUser(Long userId, Wallet wallet);
    // Para buscar una billetera indicando el propietario de la misma
    Optional<Wallet> getWalletByUser(Long userId);

    //
    Optional<Wallet> getWalletByUserNull(Long userId);

}
