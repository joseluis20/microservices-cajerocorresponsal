    package com.valtxcorresponsal.pago_prestamo_service.business.domain.services.impl;


    import com.valtxcorresponsal.pago_prestamo_service.business.api.dtos.*;
    import com.valtxcorresponsal.pago_prestamo_service.business.consume.AgenteServiceClient;
    import com.valtxcorresponsal.pago_prestamo_service.business.consume.ClienteServiceClient;
    import lombok.AllArgsConstructor;
    import lombok.extern.slf4j.Slf4j;
    import org.springframework.http.ResponseEntity;
    import org.springframework.stereotype.Service;
    import com.valtxcorresponsal.pago_prestamo_service.business.data.model.entities.TransactionEntity;
    import com.valtxcorresponsal.pago_prestamo_service.business.data.repositories.TransactionRepository;
    import com.valtxcorresponsal.pago_prestamo_service.business.domain.mappers.TransactionMapper;
    import com.valtxcorresponsal.pago_prestamo_service.business.domain.services.TransactionService;

    import java.time.LocalDateTime;
    import java.util.List;
    import java.util.Random;

    @Slf4j
    @Service
    @AllArgsConstructor
    public class TransactionServiceImpl implements TransactionService {

        private final TransactionRepository transactionRepository;
        private final TransactionMapper transactionMapper;
        private final ClienteServiceClient clienteServiceClient;
        private final AgenteServiceClient agenteServiceClient;

        @Override
        public TransactionResponseDto createTransaction(TransactionRequestDto transactionDto) {
            log.info(" Iniciando deposito para cliente: {}", transactionDto.nroDocCli());

            // 1️ Buscar cliente remoto
            var clienteRequest = new ClienteRequestDto(transactionDto.tipDocCli(), transactionDto.nroDocCli());
            var clienteResponse = clienteServiceClient.getClienteByDocument(clienteRequest);

            if (clienteResponse == null || clienteResponse.getBody() == null) {
                throw new RuntimeException("Cliente no encontrado con nroDocCli: " + transactionDto.nroDocCli());
            }
            var cliente = clienteResponse.getBody();

            // 2️ Crear request de cuentas con token incluido
            var cuentaRequest = new CuentaRequestDto(
                    transactionDto.tipDocCli(),
                    transactionDto.nroDocCli()
            );

            // 3️ Obtener cuentas del cliente desde cliente-service
            ResponseEntity<MessageCuentaResponseDto> cuentasResponse = clienteServiceClient.getAccountsByClient(cuentaRequest);
            MessageCuentaResponseDto body = cuentasResponse.getBody();

            if (body == null) {
                throw new RuntimeException("Token incorrecto o expirado");
            }

            if (body.results() == null || body.results().isEmpty()) {
                throw new RuntimeException("No se encontraron cuentas para el cliente");
            }

            List<CuentaResponseDto> cuentas = body.results();

            // 4️ Buscar la cuenta destino
            CuentaResponseDto cuenta = cuentas.stream()
                    .filter(c -> c.accountNumber().equals(transactionDto.cuentaDestino()))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Cuenta no válida o no encontrada: " + transactionDto.cuentaDestino()));

            double nuevoSaldo = cuenta.balance() + transactionDto.amount();

            // 6️ Actualizar saldo remoto del cliente (cliente-service)
            clienteServiceClient.updateSaldoCuenta(cuenta.accountNumber(), nuevoSaldo);

            // 7️ Actualizar saldo remoto del agente
            agenteServiceClient.descontarSaldo(transactionDto.agenteId(),
                    transactionDto.amount());

            // 8️ Generar número de operación único
            long numOperacion;
            Random random = new Random();
            do {
                numOperacion = 10_000_000L + random.nextInt(90_000_000);
            } while (transactionRepository.existsByOperationNumber(numOperacion));

            // 9️ Registrar transacción local
            TransactionEntity transaction = transactionMapper.toEntity(transactionDto);
            transaction.setCodAgente(transactionDto.agenteId());
            transaction.setTipDocCli(transactionDto.tipDocCli());
            transaction.setNroDocCli(transactionDto.nroDocCli());
            transaction.setTipoOperacion("DEPOSITO");
            transaction.setOperationNumber(numOperacion);
            transaction.setFecTransaccion(LocalDateTime.now());
            transaction.setCuentaDestino(transactionDto.cuentaDestino());

            TransactionEntity saved = transactionRepository.save(transaction);

            return transactionMapper.toDto(saved);
        }
    }

