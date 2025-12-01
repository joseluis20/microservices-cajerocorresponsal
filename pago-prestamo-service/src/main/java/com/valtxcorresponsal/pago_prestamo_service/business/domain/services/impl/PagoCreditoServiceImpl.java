package com.valtxcorresponsal.pago_prestamo_service.business.domain.services.impl;

import com.valtxcorresponsal.pago_prestamo_service.business.api.dtos.CuotaResponseDto;
import com.valtxcorresponsal.pago_prestamo_service.business.api.dtos.PagarCuotaClienteDto;
import com.valtxcorresponsal.pago_prestamo_service.business.api.dtos.PagoCreditoRequestDto;
import com.valtxcorresponsal.pago_prestamo_service.business.api.dtos.PagoCreditoResponseDto;
import com.valtxcorresponsal.pago_prestamo_service.business.consume.CreditoServiceClient;
import com.valtxcorresponsal.pago_prestamo_service.business.data.model.entities.TransactionEntity;
import com.valtxcorresponsal.pago_prestamo_service.business.data.repositories.TransactionRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Slf4j
@Service
@AllArgsConstructor
public class PagoCreditoServiceImpl {

    private final CreditoServiceClient creditoServiceClient;
    private final TransactionRepository transactionRepository;



    public PagoCreditoResponseDto pagarCuota(PagoCreditoRequestDto request) {

        log.info("Pagando cuota {} del crédito {}", request.nroCuota(), request.codPrest());


        // 1️ Consultar cuota en cliente-service
        CuotaResponseDto cuota = creditoServiceClient
                .obtenerCuota(request.codPrest(), request.nroCuota())
                .getBody();

        if (cuota == null) {
            throw new RuntimeException("No se encontró la cuota");
        }

        if (Boolean.TRUE.equals(cuota.estado())) {
            throw new RuntimeException("La cuota ya está pagada");
        }

        // 2️ Marcar cuota como pagada en cliente-service
        creditoServiceClient.marcarCuotaPagada(
                PagarCuotaClienteDto.builder()
                        .codPrest(request.codPrest())
                        .nroCuota(request.nroCuota())
                        .montoPagado(cuota.montoCuota())   // monto real de la cuota
                        .usuarioActualizacion("SYSTEM")  // o usuario real si lo tienes
                        .build()
        );

        // 3️ Generar número de operación
        long nroOperacion;
        Random random = new Random();

        do {
            nroOperacion = 10_000_000L + random.nextInt(90_000_000);
        } while (transactionRepository.existsByOperationNumber(nroOperacion));

        // 4️ Registrar transacción local
        TransactionEntity transaction = new TransactionEntity();
        transaction.setTipoOperacion("PAGO DE CUOTA");
        transaction.setCodPrestamo(request.codPrest());
        transaction.setNroCuota(request.nroCuota());
        transaction.setAmount(cuota.montoCuota());
        transaction.setOperationNumber(nroOperacion);
        transaction.setFecTransaccion(LocalDateTime.now());

        transactionRepository.save(transaction);

        // 5️ Respuesta
        return PagoCreditoResponseDto.builder()
                .nroOperacion(nroOperacion)
                .mensaje("Pago realizado correctamente")
                .build();


    }

}