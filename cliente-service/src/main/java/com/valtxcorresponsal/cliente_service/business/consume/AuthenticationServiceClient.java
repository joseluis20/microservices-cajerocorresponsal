package com.valtxcorresponsal.cliente_service.business.consume;

import com.valtxcorresponsal.cliente_service.business.api.dtos.LoginRequestDto;
import com.valtxcorresponsal.cliente_service.business.api.dtos.LoginResponseDto;
import com.valtxcorresponsal.cliente_service.business.api.dtos.transaction.UserRequestDto;
import com.valtxcorresponsal.cliente_service.business.api.dtos.transaction.UserResponseDto;
import com.valtxcorresponsal.cliente_service.business.data.model.entities.UserEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "autenticacion-service", url = "http://localhost:8084")
public interface AuthenticationServiceClient {

    @PostMapping("/api/v1/auth/login")
    LoginResponseDto login(@RequestBody LoginRequestDto dto);

    @PostMapping("/api/v1/auth/by-username")
    UserResponseDto getUsuarioByUsername(@RequestBody UserRequestDto request);
}
