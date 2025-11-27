package com.valtxcorresponsal.cliente_service.business.domain.services.impl;

import com.valtxcorresponsal.cliente_service.business.data.model.entities.TransactionEntity;
import com.valtxcorresponsal.cliente_service.business.data.model.entities.TransactionTypeEntity;
import com.valtxcorresponsal.cliente_service.business.data.repositories.TransactionRepository;
import com.valtxcorresponsal.cliente_service.business.domain.services.TransactionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    @Override
    public TransactionEntity saveTransaction(TransactionEntity transaction) {
        return this.transactionRepository.save(transaction);
    }



}
