package com.valtxcorresponsal.cliente_service.business.api.resources;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.valtxcorresponsal.cliente_service.business.api.dtos.ClienteRequestDto;
import com.valtxcorresponsal.cliente_service.business.api.dtos.MessageClienteResponseDto;
import com.valtxcorresponsal.cliente_service.business.api.dtos.transaction.TransactionDTO;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.valtxcorresponsal.cliente_service.business.api.dtos.ClienteResponseDto;
import com.valtxcorresponsal.cliente_service.business.domain.services.ClienteService;

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
public class ClienteResource {

  private final ClienteService clienteService;


    @PostMapping("/by-document")
    public ResponseEntity<MessageClienteResponseDto> getClientByNroDocument(@Valid @RequestBody ClienteRequestDto clienteDto) {
        try {
            Optional<ClienteResponseDto> clienteOpt = clienteService.findByNroDocument(clienteDto.nroDocument());

            //  Cliente no encontrado
            if (clienteOpt.isEmpty()) {
                MessageClienteResponseDto response = MessageClienteResponseDto.builder()
                        .statusCode(404)
                        .results(List.of())
                        .error("Cliente no encontrado")
                        .message("No existe cliente con tipo documento " + clienteDto.tipDocCli() +
                                " y número " + clienteDto.nroDocument())
                        .build();
                return ResponseEntity.status(404).body(response);
            }

            //  Cliente encontrado correctamente
            MessageClienteResponseDto response = MessageClienteResponseDto.builder()
                    .statusCode(200)
                    .results(List.of(clienteOpt.get()))
                    .error("")
                    .message("Cliente encontrado correctamente")
                    .build();

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            log.error("Error al obtener cliente por documento: {}", clienteDto.nroDocument(), e);

            MessageClienteResponseDto response = MessageClienteResponseDto.builder()
                    .statusCode(500)
                    .results(List.of())
                    .error("Internal Server Error")
                    .message("Error interno del servidor")
                    .build();

            return ResponseEntity.status(500).body(response);
        }
    }





}
