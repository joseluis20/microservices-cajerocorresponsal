package com.valtxcorresponsal.cliente_service.business.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.valtxcorresponsal.cliente_service.business.data.model.entities.ClientEntity;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<ClientEntity, Long> {

    // Para buscar un cliente por nroDocument

    Optional<ClientEntity> findByNroDocument(String nroDocument);
}
