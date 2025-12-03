package com.valtxcorresponsal.pago_prestamo_service.business.consume;


import com.valtxcorresponsal.pago_prestamo_service.business.api.dtos.TokenRequestEmail;
import com.valtxcorresponsal.pago_prestamo_service.business.api.dtos.TokenValidationRequestEmail;
import com.valtxcorresponsal.pago_prestamo_service.business.api.dtos.TokenValidationResponseEmail;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "seguridad-service", url = "http://190.235.196.163:8082")
public interface SeguridadServiceClient {

    @PostMapping("/seguridad/token/enviar")
    ResponseEntity<String> enviarToken(@RequestBody TokenRequestEmail request);

    @PostMapping("/seguridad/token/validar")
    ResponseEntity<TokenValidationResponseEmail> validarToken(
            @RequestBody TokenValidationRequestEmail request);
}
