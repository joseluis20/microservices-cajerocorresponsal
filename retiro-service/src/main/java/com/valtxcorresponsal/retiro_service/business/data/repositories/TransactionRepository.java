package com.valtxcorresponsal.retiro_service.business.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.valtxcorresponsal.retiro_service.business.data.model.entities.TransactionEntity;
import org.springframework.stereotype.Component;

@Component
public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {


    boolean existsByOperationNumber(Long operationNumber);

}
