package com.valtxcorresponsal.cliente_service.business.domain.services.impl;



import com.valtxcorresponsal.cliente_service.business.data.repositories.TransactionTypeRepository;
import com.valtxcorresponsal.cliente_service.business.domain.services.TransactionTypeService;

import java.util.Optional;


public class TransactionTypeServiceImpl implements TransactionTypeService {

    private final TransactionTypeRepository transactionTypeRepository;

    public TransactionTypeServiceImpl(TransactionTypeRepository transactionTypeRepository) {
        this.transactionTypeRepository = transactionTypeRepository;
    }

    @Override
    public Iterable<TransactionType> getTransactionsType() {
        return this.transactionTypeRepository.getTransactionsType();
    }

    @Override
    public TransactionType getTransactionType(Long id) {
        return this.transactionTypeRepository.getTransactionType(id).get();
    }

    @Override
    public TransactionType saveTransactionType(TransactionType transactionType) {
        return this.transactionTypeRepository.saveTransactionType(transactionType);
    }

    @Override
    public String deleteTransactionTypeById(Long id) {

        Optional<TransactionType> optionalTransactionType = transactionTypeRepository.getTransactionType(id);

        if (optionalTransactionType.isPresent()) {
            TransactionType transactionType = optionalTransactionType.get();
            transactionType.setActive(false);
            transactionTypeRepository.saveTransactionType(transactionType);
            return "Tipo de transaccion eliminado logicamente";
        }

        return "Tipo de transaccion no se encontro";
    }
}
