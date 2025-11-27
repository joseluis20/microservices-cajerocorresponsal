package com.valtx.corresponsal_backend.domain.port;

import com.valtx.corresponsal_backend.domain.model.TransactionType;

import java.util.Optional;

public interface TransactionTypeRepository {

    // Para mostrar todos los tipos de transaccion
    Iterable<TransactionType> getTransactionsType();
    // Para mostrar un tipo de transaccion buscando por id
    Optional<TransactionType> getTransactionType(Long id);
    // Para guardar un tipo de transaccion
    TransactionType saveTransactionType(TransactionType transactionType);

}
