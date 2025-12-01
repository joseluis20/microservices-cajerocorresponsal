package com.valtxcorresponsal.cliente_service.business.data.repositories;

import com.valtxcorresponsal.cliente_service.business.data.model.entities.AccountEntity;
import com.valtxcorresponsal.cliente_service.business.data.model.entities.TransactionEntity;
import com.valtxcorresponsal.cliente_service.business.data.model.entities.TransactionTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {

    // Para guardar una transaccion
   // TransactionEntity saveTransaction (TransactionEntity transaction);
}
