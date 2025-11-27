package com.valtx.corresponsal_backend.application.service;

import com.valtx.corresponsal_backend.domain.model.Wallet;
import com.valtx.corresponsal_backend.domain.port.WalletRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public class DomainWalletService implements WalletService{

    private final WalletRepository walletRepository;

    public DomainWalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Override
    public Iterable<Wallet> getWallets() {
        return this.walletRepository.getWallets();
    }

    @Override
    public Wallet getWalletById(Long id) {
        return this.walletRepository.getWallet(id).get();
    }

    @Override
    public Wallet saveWallet(Wallet wallet) {
        return this.walletRepository.saveWallet(wallet);
    }

    @Override
    public void deleteWallet(Long id) {
        this.walletRepository.deleteWalletById(id);
    }

    @Override
    public Wallet saveWalletByUser(Long userId, Wallet wallet) {
        return this.walletRepository.saveWalletByUser(userId, wallet);
    }

    @Override
    public Optional<Wallet> getWalletByUser(Long userId) {
        return this.walletRepository.getWalletByUser(userId);
    }

    @Override
    public Optional<Wallet> getWalletByUserNull(Long userId) {
        return this.walletRepository.getWalletByUserNull(userId);
    }

    @Override
    public Wallet rechargeWallet(Long userId, Double amount) {

        // Buscar al usuario corresponsal propietario de la billetera
        Wallet wallet = this.walletRepository.getWalletByUser(userId).get();

        // Obtener el saldo actual de la wallet
        Double currentBalance = wallet.getBalance();

        // Recargar el saldo de la wallet
        wallet.setBalance(currentBalance + amount);

        // Agregar informacion para trasabilidad del cambio
        wallet.setUpdatedAt(LocalDateTime.now());
        wallet.setTerminalUpdated("terminal_12345");
        wallet.setUserUpdated("admin");

        // Guardar la billetera con el saldo recargado
        Wallet walletUpdated = this.walletRepository.saveWallet(wallet);

        // Retornar la billetera recargada
        return walletUpdated;

    }

}
