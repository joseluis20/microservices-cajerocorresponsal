package com.valtxcorresponsal.deposito_service.business.consume;


import com.valtxcorresponsal.deposito_service.business.api.dtos.LoginRequestDto;
import com.valtxcorresponsal.deposito_service.business.api.dtos.LoginResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "autenticacion-service", url = "http://190.235.196.163:8084")
public interface AuthenticationClient {

    @PostMapping("/api/v1/auth/login")
    LoginResponseDto login(@RequestBody LoginRequestDto dto);

    @PutMapping("/api/v1/auth/agente/{id}/descontar-saldo")
    void descontarSaldo(@PathVariable Long id,
                        @RequestParam Double monto);
}
