package com.valtxcorresponsal.retiro_service.business.consume;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "agente-service", url = "http://localhost:8084", path = "/api/v1/auth")
public interface AgenteServiceClient {
    @PutMapping("/agente/{id}/aumentar-saldo")
    void aumentarSaldo(@PathVariable Long id,
                        @RequestParam Double monto);
}
