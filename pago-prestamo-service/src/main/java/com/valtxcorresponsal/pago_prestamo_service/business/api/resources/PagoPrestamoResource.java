package com.valtxcorresponsal.pago_prestamo_service.business.api.resources;

import com.valtxcorresponsal.pago_prestamo_service.business.api.dtos.MessagePagoCreditoResponseDto;
import com.valtxcorresponsal.pago_prestamo_service.business.api.dtos.PagoCreditoRequestDto;
import com.valtxcorresponsal.pago_prestamo_service.business.api.dtos.PagoCreditoResponseDto;
import com.valtxcorresponsal.pago_prestamo_service.business.domain.services.PagoCreditoService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@Slf4j
@RestController
@RequestMapping("/creditos")
@AllArgsConstructor
public class PagoPrestamoResource {

    private final PagoCreditoService pagoCreditoService;

    @PostMapping("/pagar/cuota")
    public ResponseEntity<MessagePagoCreditoResponseDto> pagarCuota(
            @RequestBody PagoCreditoRequestDto request) {

        try {
            log.info("Procesando pago de cuota {} del crédito {}", request.nroCuota(), request.nroCredito());

            PagoCreditoResponseDto responsePago = pagoCreditoService.pagarCuota(request);

            MessagePagoCreditoResponseDto response = MessagePagoCreditoResponseDto.builder()
                    .statusCode(200)
                    .results(Collections.singletonList(responsePago))
                    .error("")
                    .message("Pago de cuota realizado con éxito.")
                    .build();

            return ResponseEntity.ok(response);

        } catch (RuntimeException e) {
            log.error("Error en pago de crédito: {}", e.getMessage());

            MessagePagoCreditoResponseDto errorResponse = MessagePagoCreditoResponseDto.builder()
                    .statusCode(400)
                    .results(Collections.emptyList())
                    .error("Operación fallida")
                    .message(e.getMessage())
                    .build();

            return ResponseEntity.badRequest().body(errorResponse);

        } catch (Exception e) {
            log.error("Error inesperado en pago de crédito", e);

            MessagePagoCreditoResponseDto errorResponse = MessagePagoCreditoResponseDto.builder()
                    .statusCode(500)
                    .results(Collections.emptyList())
                    .error("Error interno del servidor")
                    .message("Error inesperado al procesar la solicitud")
                    .build();

            return ResponseEntity.internalServerError().body(errorResponse);
        }
    }
}
