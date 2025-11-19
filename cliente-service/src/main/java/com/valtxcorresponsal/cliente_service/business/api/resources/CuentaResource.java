package com.valtxcorresponsal.cliente_service.business.api.resources;

import com.valtxcorresponsal.cliente_service.business.api.dtos.*;
import com.valtxcorresponsal.cliente_service.business.api.exceptions.ResourceNotFoundException;
import com.valtxcorresponsal.cliente_service.business.consume.SeguridadServiceClient;
import com.valtxcorresponsal.cliente_service.business.domain.services.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.valtxcorresponsal.cliente_service.business.domain.services.CuentaService;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/cuentas")
@AllArgsConstructor
public class CuentaResource {

  private final CuentaService cuentaService;
  private final ClienteService clienteService;

  private final SeguridadServiceClient seguridadServiceClient;


    @PostMapping("/cliente")
    public ResponseEntity<MessageCuentaResponseDto> getAccountsByClient_NroDocument(
            @Valid @RequestBody CuentaRequestDto cuentaDto) {

        try {
            // 1️ Validar existencia del cliente
            Optional<ClienteResponseDto> clienteOpt = clienteService.findByNroDocument(cuentaDto.nroDocCli());
            if (clienteOpt.isEmpty()) {
                MessageCuentaResponseDto response = MessageCuentaResponseDto.builder()
                        .statusCode(404)
                        .results(List.of())
                        .error("Cliente no encontrado")
                        .message("No existe cliente con tipo documento " + cuentaDto.tipDocCli() +
                                " y número " + cuentaDto.nroDocCli())
                        .build();
                return ResponseEntity.status(404).body(response);
            }

            // 2️ Validar token con el microservicio de seguridad
            TokenValidationRequestEmail tokenValidationRequest =
                    new TokenValidationRequestEmail(cuentaDto.nroDocCli(), cuentaDto.token());

            TokenValidationResponseEmail tokenValidationResponse =
                    seguridadServiceClient.validarToken(tokenValidationRequest).getBody();

            if (tokenValidationResponse == null || !tokenValidationResponse.isValue()) {
                MessageCuentaResponseDto response = MessageCuentaResponseDto.builder()
                        .statusCode(500)
                        .results(List.of())
                        .error("Error Interno del Servidor")
                        .message("Token incorrecto o expirado")
                        .build();
                return ResponseEntity.status(500).body(response);
            }

            // 3️ Obtener cuentas del cliente
            List<CuentaResponseDto> cuentas = cuentaService.getAccountsByClient_NroDocument(cuentaDto.nroDocCli());

            if (cuentas.isEmpty()) {
                MessageCuentaResponseDto response = MessageCuentaResponseDto.builder()
                        .statusCode(204)
                        .results(List.of())
                        .error("")
                        .message("El cliente no tiene cuentas registradas")
                        .build();
                return ResponseEntity.status(200).body(response);
            }

            // 4️ Respuesta exitosa
            MessageCuentaResponseDto response = MessageCuentaResponseDto.builder()
                    .statusCode(200)
                    .results(cuentas)
                    .error("")
                    .message("Cuentas obtenidas correctamente")
                    .build();

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            log.error("Error general en /cuentas/cliente: ", e);
            MessageCuentaResponseDto response = MessageCuentaResponseDto.builder()
                    .statusCode(500)
                    .results(List.of())
                    .error("Error Interno del Servidor")
                    .message(e.getMessage())
                    .build();
            return ResponseEntity.status(500).body(response);
        }
    }



    @PostMapping("/cliente/deposito")
    public ResponseEntity<MessageCuentaResponseDto> getAccountsByClient_NroDocument_endeposito(
            @Valid @RequestBody CuentaRequestDto cuentaDto) {

        try {
            // 1️ Validar existencia del cliente
            Optional<ClienteResponseDto> clienteOpt = clienteService.findByNroDocument(cuentaDto.nroDocCli());
            if (clienteOpt.isEmpty()) {
                MessageCuentaResponseDto response = MessageCuentaResponseDto.builder()
                        .statusCode(404)
                        .results(List.of())
                        .error("Cliente no encontrado")
                        .message("No existe cliente con tipo documento " + cuentaDto.tipDocCli() +
                                " y número " + cuentaDto.nroDocCli())
                        .build();
                return ResponseEntity.status(404).body(response);
            }

            // 3️ Obtener cuentas del cliente
            List<CuentaResponseDto> cuentas = cuentaService.getAccountsByClient_NroDocument(cuentaDto.nroDocCli());

            if (cuentas.isEmpty()) {
                MessageCuentaResponseDto response = MessageCuentaResponseDto.builder()
                        .statusCode(204)
                        .results(List.of())
                        .error("")
                        .message("El cliente no tiene cuentas registradas")
                        .build();
                return ResponseEntity.status(200).body(response);
            }

            // 4️ Respuesta exitosa
            MessageCuentaResponseDto response = MessageCuentaResponseDto.builder()
                    .statusCode(200)
                    .results(cuentas)
                    .error("")
                    .message("Cuentas obtenidas correctamente")
                    .build();

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            log.error("Error general en /cuentas/cliente: ", e);
            MessageCuentaResponseDto response = MessageCuentaResponseDto.builder()
                    .statusCode(500)
                    .results(List.of())
                    .error("Error Interno del Servidor")
                    .message(e.getMessage())
                    .build();
            return ResponseEntity.status(500).body(response);
        }
    }


    // ✅ Endpoint que usa deposito_service
    @PutMapping("/{accountNumber}/saldo")
    public ResponseEntity<Void> updateSaldoCuenta(
            @PathVariable String accountNumber,
            @RequestParam Double nuevoSaldo) {

        cuentaService.actualizarSaldo(accountNumber, nuevoSaldo);
        return ResponseEntity.ok().build();
    }


    @Operation(description = "Para enviar el token al cliente")
    @PostMapping("/client/send_token/{nroDocument}")
    public String sendToken(@PathVariable(value = "nroDocument") String nroDocument){
        // Buscar al cliente por DNI
        Optional<ClienteResponseDto> cliente = clienteService.findByNroDocument(nroDocument);

        if (cliente.isEmpty()) {
            throw new ResourceNotFoundException("Cliente no encontrado con nroDocument: " + nroDocument);
        }

        String tokenOwner = cliente.get().nroDocument();  // DNI del cliente
        String email = cliente.get().email();             // Email del cliente

        // Crear el DTO del request
        TokenRequestEmail tokenRequestEmail = new TokenRequestEmail(tokenOwner, email);

        //  Llamar al microservicio de seguridad a través del cliente Feign
        ResponseEntity<String> response = seguridadServiceClient.enviarToken(tokenRequestEmail);

        return response.getBody();
    }



    @Operation(description = "Para enviar el token al cliente")
    @PostMapping("/client/send_token")
    public String sendToken(@Valid @RequestBody ClienteRequestDto clienteDto){
        // Buscar al cliente por DNI
        Optional<ClienteResponseDto> cliente = clienteService.findByNroDocument(clienteDto.nroDocument());

        if (cliente.isEmpty()) {
            throw new ResourceNotFoundException("Cliente no encontrado con nroDocument: " + clienteDto.nroDocument());
        }

        String tokenOwner = cliente.get().nroDocument();  // DNI del cliente
        String email = cliente.get().email();             // Email del cliente

        // Crear el DTO del request
        TokenRequestEmail tokenRequestEmail = new TokenRequestEmail(tokenOwner, email);

        //  Llamar al microservicio de seguridad a través del cliente Feign
        ResponseEntity<String> response = seguridadServiceClient.enviarToken(tokenRequestEmail);

        return response.getBody();
    }

}
