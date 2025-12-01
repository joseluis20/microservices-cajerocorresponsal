package com.valtxcorresponsal.autenticacion_service.security.service.authentication;

import com.valtxcorresponsal.autenticacion_service.security.dto.LoginRequestDto;
import com.valtxcorresponsal.autenticacion_service.security.dto.LoginResponseDto;

public interface AuthenticationService {

	LoginResponseDto login(LoginRequestDto loginRequestDTO);
         void descontarSaldo(Long idAgente, Double monto);
         void aumentarSaldo(Long idAgente, Double monto);
        Double obtenerSaldo(Long id);

}
