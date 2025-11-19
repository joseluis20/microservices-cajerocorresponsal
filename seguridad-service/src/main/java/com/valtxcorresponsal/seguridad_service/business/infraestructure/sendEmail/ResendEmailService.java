package com.valtxcorresponsal.seguridad_service.business.infraestructure.sendEmail;


import com.valtxcorresponsal.seguridad_service.business.api.dtos.*;
import com.valtxcorresponsal.seguridad_service.business.api.exceptions.EmailServiceException;
import com.resend.Resend;
import com.resend.services.emails.model.CreateEmailOptions;
import com.resend.services.emails.model.CreateEmailResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
public class ResendEmailService {

    private final Resend resend;

    @Value("${email.cc}") // Se inyecta el valor del properties
    private String ccEmail;

    // Map para guardar temporalmente los tokens
    Map<String, String> tokenMap = new HashMap<>();

    // Map para contar intentos fallidos
    private final Map<String, Integer> attemptMap = new HashMap<>();

    public ResendEmailService(Resend resend) {
        this.resend = resend;
    }

    /*
     * Para enviar token por correo
     * */
    public String enviarTokenPorCorreo(TokenRequestEmail tokenRequestEmail) {

        String subject = "Token Corresponsal Valtx";

        String token = generateToken();

        String body = "Estimado usuario, tu token es  " + token + " . Puedes usarlo solo una vez para continuar tu transacción en Corresponsal Valtx. Gracias.";

        CreateEmailOptions emailOptions = CreateEmailOptions.builder()
                .from("Corresponsal Valtx <notifications@valtx.agentecorresponsal.net.pe>")
                .to(List.of(tokenRequestEmail.getEmail()))
                    .cc(List.of(ccEmail))
                .subject(subject)
                .text(body)
                .build();

        try {

            CreateEmailResponse response = resend.emails().send(emailOptions);

            tokenMap.put(tokenRequestEmail.getUsername(), token);

        //    return "Correo enviado con ID: " + response.getId();
            return "Correo enviado con ID: " + response.getId()+ " | Token: " + token;

        } catch (Exception e) {

            throw new EmailServiceException("Error al enviar el correo", e);

        }

    }

    /*
     * Para validar un Token
     * */
    public TokenValidationResponseEmail validateToken(TokenValidationRequestEmail tokenValidationRequest) {
        String username = tokenValidationRequest.getUsername();
        String storedToken = tokenMap.get(username);
        int attempts = attemptMap.getOrDefault(username, 0);

        if (storedToken != null && storedToken.equals(tokenValidationRequest.getTokenNumber())) {
            tokenMap.remove(username);
            attemptMap.remove(username);
            TokenValidationResponseEmail tokenValidationResponse = TokenValidationResponseEmail.builder()
                    .value(true)
                    .message("Token correcto")
                    .build();
            return tokenValidationResponse;
        } else {
            attempts++;
            attemptMap.put(username, attempts);

            if (attempts >= 3) {
                tokenMap.remove(username);
                attemptMap.remove(username); // Resetear intentos fallidos después de eliminar el token
                return TokenValidationResponseEmail.builder()
                        .value(false)
                        .message("Token incorrecto. Token eliminado por demasiados intentos fallidos.")
                        .build();
            }

            TokenValidationResponseEmail tokenValidationResponse = TokenValidationResponseEmail.builder()
                    .value(false)
                    .message("Token incorrecto. Intento " + attempts + " de 3.")
                    .build();
            return tokenValidationResponse;
        }
    }

    /*
    * Para enviar codigo de recuperacion de acceso
    * */
    public AccessRecoveryResponse enviarCodigoRecuperacionAccesoPorCorreo(AccessRecoveryRequestEmail accessRecoveryRequestEmail) {

        String subject = "Recuperación de acceso a Corresponsal Valtx";

        String token = generateToken();

        String body = "Estimado usuario, tu código de recuperación de acceso es  " + token + " . Puedes usarlo solo una vez para recuperar tu acceso a Corresponsal Valtx. Gracias.";

        CreateEmailOptions emailOptions = CreateEmailOptions.builder()
                .from("Corresponsal Valtx <notifications@valtx.agentecorresponsal.net.pe>")
                .to(List.of(accessRecoveryRequestEmail.getEmail()))
                .cc(List.of(ccEmail))
                .subject(subject)
                .text(body)
                .build();

        try {

            CreateEmailResponse response = resend.emails().send(emailOptions);

            AccessRecoveryResponse accessRecoveryResponse = AccessRecoveryResponse.builder()
                    .message("Correo enviado con ID: " + response.getId())
                    .accessRecoveryCode(token)
                    .build();

            return accessRecoveryResponse;

        } catch (Exception e) {

            throw new EmailServiceException("Error al enviar el correo", e);

        }

    }

    /*
     * Para generar un Token
     * */
    private String generateToken() {
        return new DecimalFormat("000000")
                .format(new Random().nextInt(999999));
    }

}
