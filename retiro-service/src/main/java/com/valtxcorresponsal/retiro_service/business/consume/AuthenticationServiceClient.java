package com.valtxcorresponsal.retiro_service.business.consume;


import com.valtxcorresponsal.retiro_service.business.api.dtos.LoginRequestDto;
import com.valtxcorresponsal.retiro_service.business.api.dtos.LoginResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "autenticacion-service", url = "http://190.235.196.163:8084" , path = "/api/v1/auth")
public interface AuthenticationServiceClient {

    @PostMapping("/login-body")
    LoginResponseDto login(@RequestBody LoginRequestDto dto);

    @PutMapping("/agente/{id}/aumentar-saldo")
    void aumentarSaldo(@PathVariable Long id,
                       @RequestParam Double monto);
}
