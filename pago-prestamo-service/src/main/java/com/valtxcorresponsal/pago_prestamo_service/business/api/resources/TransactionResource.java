    package com.valtxcorresponsal.pago_prestamo_service.business.api.resources;


    import com.valtxcorresponsal.pago_prestamo_service.business.api.dtos.*;
    import com.valtxcorresponsal.pago_prestamo_service.business.api.exceptions.ResourceNotFoundException;
    import com.valtxcorresponsal.pago_prestamo_service.business.consume.ClienteServiceClient;
    import com.valtxcorresponsal.pago_prestamo_service.business.consume.SeguridadServiceClient;
    import io.swagger.v3.oas.annotations.Operation;
    import jakarta.validation.Valid;
    import lombok.AllArgsConstructor;
    import lombok.extern.slf4j.Slf4j;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;
    import com.valtxcorresponsal.pago_prestamo_service.business.domain.services.TransactionService;

    import java.util.Collections;

    @Slf4j
    @RestController
    @RequestMapping("/Transaction")
    @AllArgsConstructor
    public class TransactionResource {

      private final TransactionService transactionService;
      private final SeguridadServiceClient seguridadServiceClient;
      private final ClienteServiceClient clienteServiceClient;


        @PostMapping("/deposito")
        public ResponseEntity<MessageTransactionResponseDto> create(@Valid @RequestBody TransactionRequestDto transactionDto) {
            try {
                TransactionResponseDto transactionResponse = transactionService.createTransaction(transactionDto);

                MessageTransactionResponseDto response = MessageTransactionResponseDto.builder()
                        .statusCode(200)
                        .results(Collections.singletonList(transactionResponse))
                        .error("")
                        .message("Operación realizada con éxito.")
                        .build();

                return ResponseEntity.ok(response);

            } catch (RuntimeException e) {
                log.error("Error en la operación: {}", e.getMessage());

                // Si el mensaje contiene "token" -> error de token
                if (e.getMessage().toLowerCase().contains("token")) {
                    return ResponseEntity.status(500).body(
                            MessageTransactionResponseDto.builder()
                                    .statusCode(500)
                                    .results(Collections.emptyList())
                                    .error("Error Interno del Servidor")
                                    .message("Token incorrecto o expirado")
                                    .build()
                    );
                }

                // Para errores genéricos
                MessageTransactionResponseDto errorResponse = MessageTransactionResponseDto.builder()
                        .statusCode(400)
                        .results(Collections.emptyList())
                        .error("Operación no se realizó con éxito")
                        .message(e.getMessage())
                        .build();

                return ResponseEntity.badRequest().body(errorResponse);

            } catch (Exception e) {
                log.error("Error inesperado: ", e);
                MessageTransactionResponseDto errorResponse = MessageTransactionResponseDto.builder()
                        .statusCode(500)
                        .results(Collections.emptyList())
                        .error("Error Interno del Servidor")
                        .message("Error interno del servidor")
                        .build();
                return ResponseEntity.internalServerError().body(errorResponse);
            }
        }


        @Operation(description = "Para enviar el token al cliente")
        @PostMapping("/client/send_token")
        public String sendToken(@Valid @RequestBody ClienteRequestDto clienteDto){
            // 1️ Buscar cliente en cliente-service
            ResponseEntity<ClienteResponseDto> clienteResponse =
                    clienteServiceClient.getClienteByDocument(clienteDto);

            if (clienteResponse == null || clienteResponse.getBody() == null) {
                throw new ResourceNotFoundException("Cliente no encontrado con nroDocument: " + clienteDto.nroDocument());
            }

            ClienteResponseDto cliente = clienteResponse.getBody();
            String tokenOwner = cliente.nroDocument();
            String email = cliente.email();

            // 2️ Crear DTO del request
            TokenRequestEmail tokenRequestEmail = new TokenRequestEmail(tokenOwner, email);

            // 3️ Llamar al microservicio de seguridad
            ResponseEntity<String> response = seguridadServiceClient.enviarToken(tokenRequestEmail);

            return response.getBody();
        }


    }
