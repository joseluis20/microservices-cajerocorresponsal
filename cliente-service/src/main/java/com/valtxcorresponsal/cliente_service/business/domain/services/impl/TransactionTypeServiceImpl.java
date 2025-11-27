package com.valtxcorresponsal.cliente_service.business.domain.services.impl;



import com.valtxcorresponsal.cliente_service.business.data.model.entities.TransactionTypeEntity;
import com.valtxcorresponsal.cliente_service.business.data.repositories.TransactionTypeRepository;
import com.valtxcorresponsal.cliente_service.business.domain.services.TransactionTypeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class TransactionTypeServiceImpl implements TransactionTypeService {

    private final TransactionTypeRepository transactionTypeRepository;




    @Override
    public TransactionTypeEntity getTransactionType(Long id) {
        return transactionTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction Type not found: " + id));
    }


}
