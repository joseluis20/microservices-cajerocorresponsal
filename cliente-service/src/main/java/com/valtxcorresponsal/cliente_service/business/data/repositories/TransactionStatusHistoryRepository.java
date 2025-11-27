package com.valtxcorresponsal.cliente_service.business.data.repositories;

import com.valtxcorresponsal.cliente_service.business.data.model.entities.AccountEntity;
import com.valtxcorresponsal.cliente_service.business.data.model.entities.TransactionStatusHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionStatusHistoryRepository extends JpaRepository<TransactionStatusHistoryEntity, Long> {

    // Para guardar un estado de una transaccion en el historial de estado de transacciones
   // TransactionStatusHistoryEntity saveTransactionStatusHistory(TransactionStatusHistoryEntity transactionStatusHistory);

}
