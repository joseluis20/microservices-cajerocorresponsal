package com.valtxcorresponsal.pago_prestamo_service.business.consume;

import com.valtxcorresponsal.pago_prestamo_service.business.api.dtos.CuotaResponseDto;
import com.valtxcorresponsal.pago_prestamo_service.business.api.dtos.PagarCuotaClienteDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "credito-service", url = "http://localhost:8082")
public interface CreditoServiceClient {

    @GetMapping("/creditos/{codPrest}/cuotas/{nroCuota}")
    ResponseEntity<CuotaResponseDto> obtenerCuota(
            @PathVariable String codPrest,
            @PathVariable Integer nroCuota);

    @PostMapping("/creditos/cuotas/pagar")
    ResponseEntity<CuotaResponseDto> marcarCuotaPagada(@RequestBody PagarCuotaClienteDto request);
}
