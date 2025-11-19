package com.valtxcorresponsal.retiro_service.business.consume;


import com.valtxcorresponsal.retiro_service.business.api.dtos.LoginRequestDto;
import com.valtxcorresponsal.retiro_service.business.api.dtos.LoginResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "autenticacion-service", url = "http://localhost:8084")
public interface AuthenticationClient {

    @PostMapping("/api/v1/auth/login-body")
    LoginResponseDto login(@RequestBody LoginRequestDto dto);
}
