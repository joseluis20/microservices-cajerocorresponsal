    package com.valtxcorresponsal.cliente_service.business.domain.services;

    import com.valtxcorresponsal.cliente_service.business.data.model.entities.TransactionEntity;
    import com.valtxcorresponsal.cliente_service.business.data.model.entities.TransactionTypeEntity;

    public interface TransactionService {

        TransactionEntity saveTransaction (TransactionEntity transaction);
    }
