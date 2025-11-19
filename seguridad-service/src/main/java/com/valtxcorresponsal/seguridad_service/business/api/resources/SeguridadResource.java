package com.valtxcorresponsal.seguridad_service.business.api.resources;


import com.valtxcorresponsal.seguridad_service.business.api.dtos.*;
import com.valtxcorresponsal.seguridad_service.business.infraestructure.sendEmail.ResendEmailService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/seguridad")
@AllArgsConstructor
public class SeguridadResource {

    private final ResendEmailService emailService;

    @PostMapping("/token/enviar")
    public ResponseEntity<String> enviarToken(@RequestBody TokenRequestEmail request) {
        String result = emailService.enviarTokenPorCorreo(request);
        return ResponseEntity.ok(result);
    }



    @PostMapping("/token/validar")
    public ResponseEntity<TokenValidationResponseEmail> validarToken(
            @RequestBody TokenValidationRequestEmail request) {
        TokenValidationResponseEmail result = emailService.validateToken(request);
        return ResponseEntity.ok(result);

    }

    @PostMapping("/recuperar")
    public ResponseEntity<AccessRecoveryResponse> recuperarAcceso(
            @RequestBody AccessRecoveryRequestEmail request) {
        AccessRecoveryResponse result = emailService.enviarCodigoRecuperacionAccesoPorCorreo(request);
        return ResponseEntity.ok(result);
    }
}
