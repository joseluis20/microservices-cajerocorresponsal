package com.valtxcorresponsal.cliente_service.business.data.repositories;

import com.valtxcorresponsal.cliente_service.business.data.model.entities.ClientEntity;
import com.valtxcorresponsal.cliente_service.business.data.model.entities.TransactionTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransactionTypeRepository extends JpaRepository<TransactionTypeEntity, Long> {


}
