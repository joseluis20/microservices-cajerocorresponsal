package com.valtxcorresponsal.cliente_service.business.domain.services.impl;

import com.valtxcorresponsal.cliente_service.business.api.dtos.CuentaResponseDto;
import com.valtxcorresponsal.cliente_service.business.api.dtos.CuotaResponseDto;
import com.valtxcorresponsal.cliente_service.business.api.dtos.transaction.UserRequestDto;
import com.valtxcorresponsal.cliente_service.business.api.dtos.transaction.UserResponseDto;
import com.valtxcorresponsal.cliente_service.business.api.exceptions.ResourceNotFoundException;
import com.valtxcorresponsal.cliente_service.business.consume.AuthenticationServiceClient;
import com.valtxcorresponsal.cliente_service.business.data.model.entities.AccountEntity;
import com.valtxcorresponsal.cliente_service.business.data.model.entities.CuotaEntity;
import com.valtxcorresponsal.cliente_service.business.data.model.entities.UserEntity;
import com.valtxcorresponsal.cliente_service.business.data.repositories.CreditoRepository;
import com.valtxcorresponsal.cliente_service.business.data.repositories.CuentaRepository;
import com.valtxcorresponsal.cliente_service.business.data.repositories.CuotaRepository;
import com.valtxcorresponsal.cliente_service.business.domain.mappers.CuentaMapper;
import com.valtxcorresponsal.cliente_service.business.domain.services.CreditoService;
import com.valtxcorresponsal.cliente_service.business.domain.services.CuentaService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class CreditoServiceImpl implements CreditoService {

    private final CreditoRepository creditoRepository;
    private final CuotaRepository cuotaRepository;

    @Override
    public List<String> obtenerNrosCredito(Long idCliente) {
        List<String> creditos = creditoRepository.findNrosCreditoByIdCliente(idCliente);

        if (creditos.isEmpty()) {
            throw new RuntimeException("El cliente no tiene créditos registrados");
        }

        return creditos;
    }


    @Override
    public List<CuotaResponseDto> obtenerCuotasPorNroCredito(String nroCredito) {

        List<CuotaEntity> cuotas = cuotaRepository.findByNroCredito(nroCredito);

        return cuotas.stream()
                .map(c -> CuotaResponseDto.builder()
                        .nroCuota(c.getNroCuota())
                        .montoCuota(c.getMontoTotal())
                        .build())
                .collect(Collectors.toList());
    }

    // -------------------------------------------------------
    //  Obtener cuota especifica
    // -------------------------------------------------------
    public CuotaResponseDto obtenerCuotaEspecifica(String nroCredito, Integer nroCuota) {

        CuotaEntity cuota = cuotaRepository.findByNroCreditoAndNroCuota(nroCredito, nroCuota)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Cuota no existe para crédito " + nroCredito));

        return CuotaResponseDto.builder()
                .nroCuota(cuota.getNroCuota())
                .montoCuota(cuota.getMontoTotal())
                .estado(cuota.getEstado())
                .fechaPago(cuota.getFechaPago())
                .build();
    }

    // -------------------------------------------------------
    //   Marcar cuota como pagada
    // -------------------------------------------------------
    public CuotaResponseDto marcarCuotaPagada(String nroCredito, Integer nroCuota) {

        CuotaEntity cuota = cuotaRepository.findByNroCreditoAndNroCuota(nroCredito, nroCuota)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Cuota no existe para crédito " + nroCredito));

        if (Boolean.TRUE.equals(cuota.getEstado())) {
            throw new RuntimeException("La cuota ya estaba pagada");
        }

        cuota.setEstado(true);
        cuota.setFechaPago(LocalDate.now());
        cuotaRepository.save(cuota);

        return CuotaResponseDto.builder()
                .nroCuota(cuota.getNroCuota())
                .montoCuota(cuota.getMontoTotal())
                .estado(true)
                .fechaPago(cuota.getFechaPago())
                .build();
    }

}
