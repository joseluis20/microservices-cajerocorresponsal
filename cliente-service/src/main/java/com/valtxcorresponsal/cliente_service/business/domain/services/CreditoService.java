package com.valtxcorresponsal.cliente_service.business.domain.services;

import com.valtxcorresponsal.cliente_service.business.api.dtos.CuotaResponseDto;
import com.valtxcorresponsal.cliente_service.business.data.model.entities.CuotaEntity;

import java.util.List;

public interface CreditoService {


    List<String> obtenerNrosCredito(Long idCliente) ;
    List<CuotaResponseDto> obtenerCuotasPorNroCredito(String nroCredito);
    CuotaResponseDto obtenerCuotaEspecifica(String codPrest, Integer nroCuota);
    CuotaResponseDto marcarCuotaPagada(String codPrest, Integer nroCuota);
}
