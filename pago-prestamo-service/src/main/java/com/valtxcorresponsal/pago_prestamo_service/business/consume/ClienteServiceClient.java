package com.valtxcorresponsal.pago_prestamo_service.business.consume;

import com.valtxcorresponsal.pago_prestamo_service.business.api.dtos.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "cliente-service", url = "http://190.235.196.163:8083")
public interface ClienteServiceClient {


    @PostMapping("/clientes/by-document")
    ResponseEntity<ClienteResponseDto> getClienteByDocument(@RequestBody ClienteRequestDto request);



    @PostMapping("/cuentas/cliente/deposito")
    ResponseEntity<MessageCuentaResponseDto> getAccountsByClient(@RequestBody CuentaRequestDto request);


    @PutMapping("/cuentas/{accountNumber}/saldo")
    ResponseEntity<Void> updateSaldoCuenta(
            @PathVariable("accountNumber") String accountNumber,
            @RequestParam("nuevoSaldo") Double nuevoSaldo
    );


    @GetMapping("/clientes/creditos/{nroCredito}/cuotas/{nroCuota}")
    ResponseEntity<CuotaResponseDto> obtenerCuota(
            @PathVariable String nroCredito,
            @PathVariable Integer nroCuota);

    @PostMapping("/clientes/creditos/cuotas/pagar")
    ResponseEntity<CuotaResponseDto> marcarCuotaPagada(@RequestBody PagarCuotaClienteDto request);


}