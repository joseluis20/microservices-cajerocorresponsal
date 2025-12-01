package com.valtxcorresponsal.cliente_service.business.api.resources;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.github.f4b6a3.uuid.UuidCreator;
import com.valtxcorresponsal.cliente_service.business.api.dtos.*;
import com.valtxcorresponsal.cliente_service.business.api.dtos.transaction.TransactionDTOj;
import com.valtxcorresponsal.cliente_service.business.api.exceptions.ResourceNotFoundException;
import com.valtxcorresponsal.cliente_service.business.consume.SeguridadServiceClient;
import com.valtxcorresponsal.cliente_service.business.data.model.entities.*;
import com.valtxcorresponsal.cliente_service.business.domain.services.*;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@RestController
@RequestMapping("/clientes")
@AllArgsConstructor
public class CreditoResource {

  private final CuentaService cuentaService;
  private final ClienteService clienteService;
    private final CreditoService creditoService;


    @GetMapping("/creditos/{idCliente}")
    @Operation(summary = "Obtiene los números de crédito de un cliente")
    public ResponseEntity<MessageCreditoResponseDto> obtenerCreditos(@PathVariable Long idCliente) {

        try {
            List<String> creditos = creditoService.obtenerNrosCredito(idCliente);

            // Si no tiene créditos
            if (creditos.isEmpty()) {
                MessageCreditoResponseDto response = MessageCreditoResponseDto.builder()
                        .statusCode(204)
                        .results(List.of())
                        .error("")
                        .message("El cliente no posee créditos registrados")
                        .build();
                return ResponseEntity.status(200).body(response);
            }

            // Respuesta correcta
            MessageCreditoResponseDto response = MessageCreditoResponseDto.builder()
                    .statusCode(200)
                    .results(creditos)
                    .error("")
                    .message("Créditos obtenidos correctamente")
                    .build();

            return ResponseEntity.ok(response);

        } catch (ResourceNotFoundException e) {

            MessageCreditoResponseDto response = MessageCreditoResponseDto.builder()
                    .statusCode(404)
                    .results(List.of())
                    .error("Cliente no encontrado")
                    .message(e.getMessage())
                    .build();

            return ResponseEntity.status(404).body(response);

        } catch (Exception e) {

            MessageCreditoResponseDto response = MessageCreditoResponseDto.builder()
                    .statusCode(500)
                    .results(List.of())
                    .error("Error interno del servidor")
                    .message(e.getMessage())
                    .build();

            return ResponseEntity.status(500).body(response);
        }
    }


    @GetMapping("/creditos/{nroCredito}/cuotas")
    public ResponseEntity<MessageCuotaResponseDto> obtenerCuotas(
            @PathVariable String nroCredito) {

        try {
            List<CuotaResponseDto> cuotas = creditoService.obtenerCuotasPorNroCredito(nroCredito);

            if (cuotas.isEmpty()) {
                MessageCuotaResponseDto response = MessageCuotaResponseDto.builder()
                        .statusCode(204)
                        .results(List.of())
                        .error("")
                        .message("El crédito no tiene cuotas registradas")
                        .build();
                return ResponseEntity.status(200).body(response);
            }

            MessageCuotaResponseDto response = MessageCuotaResponseDto.builder()
                    .statusCode(200)
                    .results(cuotas)
                    .error("")
                    .message("Cuotas obtenidas correctamente")
                    .build();

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            MessageCuotaResponseDto response = MessageCuotaResponseDto.builder()
                    .statusCode(500)
                    .results(List.of())
                    .error("Error interno del servidor")
                    .message(e.getMessage())
                    .build();
            return ResponseEntity.status(500).body(response);
        }
    }


    @GetMapping("/creditos/{codPrest}/cuotas/{nroCuota}")
    public ResponseEntity<CuotaResponseDto> obtenerCuotaEspecifica(
            @PathVariable String codPrest,
            @PathVariable Integer nroCuota) {

        try {
            CuotaResponseDto cuota = creditoService.obtenerCuotaEspecifica(codPrest, nroCuota);

            return ResponseEntity.ok(cuota);

        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(404).body(null);

        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @PostMapping("/creditos/cuotas/pagar")
    public ResponseEntity<CuotaResponseDto> pagarCuota(@RequestBody PagoCreditoRequestDto request) {
        try {

            CuotaResponseDto cuotaPagada = creditoService.marcarCuotaPagada(
                    request.codPrest(),
                    request.nroCuota()
            );

            return ResponseEntity.ok(cuotaPagada);

        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(404).body(null);

        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

}
