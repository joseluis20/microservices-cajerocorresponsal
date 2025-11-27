package com.valtx.corresponsal_backend.application.service;

import com.valtx.corresponsal_backend.domain.model.TransactionType;

import java.util.Optional;

public interface TransactionTypeService {

    // Para mostrar todos los tipos de transaccion
    Iterable<TransactionType> getTransactionsType();
    // Para mostrar un tipo de transaccion buscando por id
    TransactionType getTransactionType(Long id);
    // Para guardar un tipo de transaccion
    TransactionType saveTransactionType(TransactionType transactionType);
    // Para eliminar tipo de transaccion buscandolo por id
    public String deleteTransactionTypeById(Long id);

}
