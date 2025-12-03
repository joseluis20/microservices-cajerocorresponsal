package com.valtxcorresponsal.pago_prestamo_service.business.consume;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "agente-service", url = "http://190.235.196.163:8084", path = "/api/v1/auth")
public interface AgenteServiceClient {
    @PutMapping("/agente/{id}/descontar-saldo")
    void descontarSaldo(@PathVariable Long id,
                        @RequestParam Double monto);
}
