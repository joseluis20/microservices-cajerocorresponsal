package com.valtxcorresponsal.cliente_service.business.api.resources;

import com.valtxcorresponsal.cliente_service.business.api.dtos.*;
import com.valtxcorresponsal.cliente_service.business.api.dtos.transaction.TransactionDTO;
import com.github.f4b6a3.uuid.UuidCreator;
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
import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@RestController
@RequestMapping("/cuentas")
@AllArgsConstructor
public class CuentaResource {

  private final CuentaService cuentaService;
  private final ClienteService clienteService;
  private final TransactionTypeService transactionTypeService;
  private final WalletService walletService;

  private final SeguridadServiceClient seguridadServiceClient;
  private final TransactionService transactionService;
  private final TransactionStatusHistoryService transactionStatusHistoryService;


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



    @Operation(description = "Para enviar el token al cliente. En transactionTypeId indicar 1 para retiro, 2 para depósito, 3 para pago de servicios o 5 para transferencia a terceros")
    @PostMapping("/client/send_token/his/{nroDocument}/{transactionTypeId}")
    public ResponseEntity<TransactionDTO> sendTokenWhitHis(@RequestHeader("Authorization") String authHeader, @PathVariable(value = "nroDocument") String nroDocument, @PathVariable(value = "transactionTypeId") Long transactionTypeId){

        /* VALIDACION CON EL PAYLOAD DEL JWT */
        // Obtener el jwt
        String token = authHeader.replace("Bearer ", "");
        DecodedJWT decodedJWT = JWT.decode(token);
        // Obtener el username del payload del jwt
        String usernameFromJwt = decodedJWT.getSubject();
        // Obtener al user
        UserEntity user = cuentaService.findUserEntityByUsername(usernameFromJwt);

        // Validacion de si el usuario esta activo
        if(user.getEstado()!=1){
            throw new ResourceNotFoundException("Su usuario está inactivo, no puede realizar operaciones en este momento");
        }

        //Buscar al cliente por DNI
        ClientEntity cliente = clienteService.getClientByNroDocument(nroDocument);

        // Validacion de si el cliente esta activo
        if(!cliente.isActive()){
            throw new ResourceNotFoundException("El cliente está inhabilitado, no puede realizar operaciones en este momento");
        }

        // Validacion de que el cliente tenga al menos una cuenta activa. Si todas las cuentas estan inactivas, lanzar mensaje de error
        Iterable<AccountEntity> accounts = cuentaService.getAccountsByNroDocument(cliente.getNroDocument());
        List<AccountEntity> accountList = StreamSupport.stream(accounts.spliterator(), false).collect(Collectors.toList());

        boolean atLeastOneActiveAccount = false;

        for(AccountEntity account:accountList){
            if(account.isActive()){
                atLeastOneActiveAccount = true;
                break;
            }
        }

        if(!atLeastOneActiveAccount){
            throw new ResourceNotFoundException("El cliente no tiene cuentas activas");
        }

        String tokenOwner = cliente.getNroDocument();  // DNI del cliente
        String email = cliente.getEmail();             // Email del cliente

        TokenRequestEmail tokenRequestEmail = new TokenRequestEmail(tokenOwner, email);

        // Enviar el token por correo, Llamar al microservicio de seguridad a través del cliente Feign
        ResponseEntity<String> sendTokenResponse = seguridadServiceClient.enviarToken(tokenRequestEmail);

        /* INICIAR LA TRANSACCION */
        //Buscar tipo de transaccion
        TransactionEntity transactionType = transactionTypeService.getTransactionType(transactionTypeId);
        //Buscar la wallet
        WalletEntity wallet = walletService.getWalletByUser(user.getIdPerfil()).get();
        //Construir transaction.
        TransactionTypeEntity transaction = TransactionTypeEntity.builder()
                .transactionType(transactionType)
                .wallet(wallet)
                .active(false)

                .createdAt(LocalDateTime.now())
                .terminalCreated("Terminal_12345")
                .userCreated(user.getUserName())
                //.operationNumber(UUID.randomUUID().toString())
                .operationNumber(UuidCreator.getTimeOrderedEpoch().toString()) //se usa uuid v7 para el numero de operacion
                .year(LocalDate.now().getYear())
                .month(LocalDate.now().getMonthValue())
                .day(LocalDate.now().getDayOfMonth())
                .build();

        //Guardar transaction
        TransactionTypeEntity savedTransaction = transactionService.saveTransaction(transaction);
        //Registrar en la tabla de historial de estados de transacciones
        TransactionStatusHistoryEntity transactionStatusHistory = TransactionStatusHistoryEntity.builder()
                .transaction(savedTransaction)
                .status("Iniciada")
                .createdAt(LocalDateTime.now())
                .build();
        transactionStatusHistoryService.saveTransactionStatusHistory(transactionStatusHistory);

        // Enviar el token por correo
        //String sendTokenResponse = sendGridEmailService.enviarTokenPorCorreo(tokenRequestEmail);

        TransactionDTOj transactionDTOj = TransactionDTOj.builder()
                .message(sendTokenResponse.getBody())
                .idTransaction(savedTransaction.getId())
                .transactionType(savedTransaction.getTransactionType().getName())
                .email(email)
                .build();

        //return sendGridEmailService.enviarTokenPorCorreo(tokenRequestEmail);
        return new ResponseEntity<>(transactionDTOj, HttpStatus.CREATED);


    }

}
