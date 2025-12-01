package com.valtxcorresponsal.cliente_service.business.data.repositories;
import com.valtxcorresponsal.cliente_service.business.data.model.entities.CuotaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CuotaRepository extends JpaRepository<CuotaEntity, Long> {

    @Query("SELECT c FROM CuotaEntity c WHERE c.credito.nroCredito = :nroCredito ORDER BY c.nroCuota ASC")
    List<CuotaEntity> findByNroCredito(String nroCredito);

    // Nuevo metodo necesario
    Optional<CuotaEntity> findByCodPrestAndNroCuota(String codPrest, Integer nroCuota);
}