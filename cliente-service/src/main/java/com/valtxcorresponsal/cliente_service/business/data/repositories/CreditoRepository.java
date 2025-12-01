package com.valtxcorresponsal.cliente_service.business.data.repositories;

import com.valtxcorresponsal.cliente_service.business.data.model.entities.AccountEntity;
import com.valtxcorresponsal.cliente_service.business.data.model.entities.CreditoEntity;
import feign.Param;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CreditoRepository extends JpaRepository<CreditoEntity, Long> {

    @Query("SELECT c.nroCredito FROM CreditoEntity c WHERE c.cliente.id = :idCliente")
    List<String> findNrosCreditoByIdCliente(Long idCliente);


    // Para obtener un crédito específico por su código
    //Optional<CreditoEntity> findByCodPrest(String codPrest);
}
