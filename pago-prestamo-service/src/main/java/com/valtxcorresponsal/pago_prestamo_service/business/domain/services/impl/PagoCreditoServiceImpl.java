package com.valtxcorresponsal.pago_prestamo_service.business.domain.services.impl;

import com.valtxcorresponsal.pago_prestamo_service.business.api.dtos.CuotaResponseDto;
import com.valtxcorresponsal.pago_prestamo_service.business.api.dtos.PagoCreditoRequestDto;
import com.valtxcorresponsal.pago_prestamo_service.business.api.dtos.PagoCreditoResponseDto;
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

    //private final CreditoServiceClient creditoServiceClient;
    private final TransactionRepository transactionRepository;

    /************
    public PagoCreditoResponseDto pagarCuota(PagoCreditoRequestDto request) {

        log.info("Pagando cuota {} del crédito {}", request.nroCuota(), request.codPrest());


        // 1️⃣ Consultar cuota en cliente-service
        CuotaResponseDto cuota = creditoServiceClient
                .obtenerCuota(request.codPrest(), request.nroCuota())
                .getBody();

        if (cuota == null) {
            throw new RuntimeException("No se encontró la cuota");
        }

        if (Boolean.TRUE.equals(cuota.pagado())) {
            throw new RuntimeException("La cuota ya está pagada");
        }

        // 2️⃣ Marcar cuota como pagada en cliente-service
        creditoServiceClient.marcarCuotaPagada(
                new PagarCuotaClienteDto(request.codPrest(), request.nroCuota())
        );

        // 3️⃣ Generar número de operación
        long nroOperacion;
        Random random = new Random();

        do {
            nroOperacion = 10_000_000L + random.nextInt(90_000_000);
        } while (transactionRepository.existsByOperationNumber(nroOperacion));

        // 4️⃣ Registrar transacción local
        TransactionEntity trans = new TransactionEntity();
        trans.setTipoOperacion("PAGO_CUOTA");
        trans.setCodPrestamo(request.codPrest());
        trans.setNroCuota(request.nroCuota());
        trans.setMonto(cuota.monto());
        trans.setOperationNumber(nroOperacion);
        trans.setFecTransaccion(LocalDateTime.now());

        transactionRepository.save(trans);

        // 5️⃣ Respuesta
        return PagoCreditoResponseDto.builder()
                .nroOperacion(nroOperacion)
                .mensaje("Pago realizado correctamente")
                .build();


    }
     */
}