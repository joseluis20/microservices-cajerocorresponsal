package com.valtxcorresponsal.cliente_service.business.domain.services;

import com.valtxcorresponsal.cliente_service.business.data.model.entities.TransactionStatusHistoryEntity;

public interface TransactionStatusHistoryService {

    // Para guardar un estado de una transaccion en el historial de estado de transacciones
    TransactionStatusHistoryEntity saveTransactionStatusHistory(TransactionStatusHistoryEntity transactionStatusHistory);

}
