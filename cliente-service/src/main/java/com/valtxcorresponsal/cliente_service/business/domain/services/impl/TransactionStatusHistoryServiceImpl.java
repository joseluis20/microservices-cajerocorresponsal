package com.valtxcorresponsal.cliente_service.business.domain.services.impl;

import com.valtxcorresponsal.cliente_service.business.data.model.entities.TransactionStatusHistoryEntity;
import com.valtxcorresponsal.cliente_service.business.data.repositories.TransactionStatusHistoryRepository;
import com.valtxcorresponsal.cliente_service.business.domain.services.TransactionStatusHistoryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class TransactionStatusHistoryServiceImpl implements TransactionStatusHistoryService {

    private final TransactionStatusHistoryRepository transactionStatusHistoryRepository;


    @Override
    public TransactionStatusHistoryEntity saveTransactionStatusHistory(TransactionStatusHistoryEntity transactionStatusHistory) {
        return this.transactionStatusHistoryRepository.save(transactionStatusHistory);
    }

}
