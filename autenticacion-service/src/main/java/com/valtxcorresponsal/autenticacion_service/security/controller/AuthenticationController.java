package com.valtxcorresponsal.autenticacion_service.security.controller;

import com.valtxcorresponsal.autenticacion_service.security.dto.LoginRequestDto;
import com.valtxcorresponsal.autenticacion_service.security.dto.LoginResponseDto;
import com.valtxcorresponsal.autenticacion_service.security.entity.AgenteEntity;
import com.valtxcorresponsal.autenticacion_service.security.entity.UserEntity;
import com.valtxcorresponsal.autenticacion_service.security.repository.UserRepository;
import com.valtxcorresponsal.autenticacion_service.security.service.authentication.AuthenticationService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationService authenticationService;
  private final UserRepository userRepository;

    @PostMapping("/login")
  public ResponseEntity<LoginResponseDto> signinHeader(HttpServletResponse response,
                                                       @Validated @RequestBody LoginRequestDto request) {

    String token = authenticationService.login(request).token();
    HttpHeaders responseHeaders = new HttpHeaders();
    responseHeaders.set("Authorization", "Bearer " + token);
    return ResponseEntity.ok().headers(responseHeaders).build();
  }

  @PostMapping("/login-body")
  public ResponseEntity<Map<String, Object>> signinBody(HttpServletResponse response2,
                                                        @RequestBody LoginRequestDto request) {
    LoginResponseDto response = authenticationService.login(request);
    log.info("token {}", response.token());

    Map<String, Object> resp = new HashMap<>();
    resp.put("token", response.token());
    resp.put("ageId", response.ageId());
    resp.put("ageNom", response.ageNom());
    resp.put("ageApePat", response.ageApePat());
    resp.put("ageApeMat", response.ageApeMat());
    resp.put("ageNomAge", response.ageNomAge());
    resp.put("ageCor", response.ageCor());
    resp.put("ageTelf", response.ageTelf());
    resp.put("ageTelf2", response.ageTelf2());
    resp.put("ageCel", response.ageCel());
    resp.put("ageCel2", response.ageCel2());
    resp.put("ageSaldo", response.ageSaldo());


    return ResponseEntity.ok().body(resp);
  }

    @PutMapping("/agente/{id}/descontar-saldo")
    public ResponseEntity<Void> descontarSaldo(
            @PathVariable Long id,
            @RequestParam Double monto) {

        authenticationService.descontarSaldo(id, monto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/agente/{id}/aumentar-saldo")
    public ResponseEntity<Void> aumentarSaldo(
            @PathVariable Long id,
            @RequestParam Double monto) {

        authenticationService.aumentarSaldo(id, monto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/by-username")
    public ResponseEntity<UserEntity> getByUsername(@RequestBody Map<String, String> body) {
        String username = body.get("username");
        UserEntity user = userRepository.findByUserNameIgnoreCase(username);
        return ResponseEntity.ok(user);
    }


/*
    @GetMapping("/agente/saldo/{id}")
    public ResponseEntity<Optional<AgenteEntity>> obtenerSaldo2(@PathVariable Long id) {
        log.info("Llamando saldo con id = {}", id);
        Optional<AgenteEntity> agente = authenticationService.obtenerSaldo(id);
        log.info("Resultado servicio = {}", agente);
        return ResponseEntity.ok(agente);
    }
*/
    @GetMapping("/agente/saldo/{id}")
    public Double obtenerSaldo(@PathVariable Long id) {

        Double agenteSaldo = authenticationService.obtenerSaldo(id);

        return ResponseEntity.ok(agenteSaldo).getBody();
    }
}



