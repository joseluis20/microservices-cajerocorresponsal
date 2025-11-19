package com.valtxcorresponsal.cliente_service.business.consume;

import com.valtxcorresponsal.cliente_service.business.api.dtos.LoginRequestDto;
import com.valtxcorresponsal.cliente_service.business.api.dtos.LoginResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "autenticacion-service", url = "http://localhost:8084")
public interface AuthenticationClient {

    @PostMapping("/api/v1/auth/login")
    LoginResponseDto login(@RequestBody LoginRequestDto dto);
}
