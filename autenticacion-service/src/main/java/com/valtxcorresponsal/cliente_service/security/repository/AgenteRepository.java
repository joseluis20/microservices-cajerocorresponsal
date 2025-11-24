package com.valtxcorresponsal.cliente_service.security.repository;

import com.valtxcorresponsal.cliente_service.security.entity.AgenteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AgenteRepository extends JpaRepository<AgenteEntity, Long> {

 //   AgenteEntity saveAgente(Integer businessId, AgenteEntity shopAgent);
 Optional<AgenteEntity> findByAgeId(Long ageId);


}
