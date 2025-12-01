package com.valtxcorresponsal.autenticacion_service.security.repository;

import com.valtxcorresponsal.autenticacion_service.security.entity.AgenteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AgenteRepository extends JpaRepository<AgenteEntity, Long> {

 //   AgenteEntity saveAgente(Integer businessId, AgenteEntity shopAgent);
 Optional<AgenteEntity> findByAgeId(Long ageId);

    Double findAgeSaldoByAgeId(Long ageId);


}
