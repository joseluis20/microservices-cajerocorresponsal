package com.valtxcorresponsal.cliente_service.security.service.authentication;

import com.valtxcorresponsal.cliente_service.security.dto.LoginRequestDto;
import com.valtxcorresponsal.cliente_service.security.dto.LoginResponseDto;
import com.valtxcorresponsal.cliente_service.security.entity.AgenteEntity;
import com.valtxcorresponsal.cliente_service.security.repository.AgenteRepository;
import com.valtxcorresponsal.cliente_service.security.repository.UserRepository;
import com.valtxcorresponsal.cliente_service.security.service.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;


import java.util.Optional;

import static java.util.Objects.isNull;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

  private final UserDetailsService userDetailsService;

  private final JwtService jwtService;

  private final AuthenticationManager authenticationManager;

  private final AgenteRepository agenteRepository;

  private final UserRepository userRepository;

  @Override
  public LoginResponseDto login(LoginRequestDto loginRequestDTO) {

    log.info("loginRequestDTO {}", loginRequestDTO);

    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginRequestDTO.userName(), loginRequestDTO.password()));

    UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequestDTO.userName());

    log.info("userDetails {}", userDetails);

    if (isNull(userDetails)) {
      throw new IllegalArgumentException("Invalid user or password.");
    }

    String token = jwtService.generateToken(userDetails);

    // registrar sesion exitosa

    Long codAgente= userRepository.findCodAgenteByUserName(loginRequestDTO.userName());

    Optional<AgenteEntity> agente=agenteRepository.findByAgeId(codAgente);

    return new LoginResponseDto(token,
            agente.get().getAgeId(),
            loginRequestDTO.userName(),
            agente.get().getAgeNom(),
            agente.get().getAgeApePat(),
            agente.get().getAgeApeMat(),
            agente.get().getAgeSaldo(),
            agente.get().getAgeNomAge(),
            agente.get().getAgeCel(),
            agente.get().getAgeCel2(),
            agente.get().getAgeTelf(),
            agente.get().getAgeTelf2(),
            agente.get().getAgeCor()
             )
            ;
  }

    @Override
    public void descontarSaldo(Long idAgente, Double monto) {

        AgenteEntity agente = agenteRepository.findById(idAgente)
                .orElseThrow(() -> new RuntimeException("Agente no encontrado"));

        agente.setAgeSaldo(agente.getAgeSaldo() - monto);

        agenteRepository.save(agente);
    }


    @Override
    public void aumentarSaldo(Long idAgente, Double monto) {

        AgenteEntity agente = agenteRepository.findById(idAgente)
                .orElseThrow(() -> new RuntimeException("Agente no encontrado"));

        agente.setAgeSaldo(agente.getAgeSaldo() + monto);

        agenteRepository.save(agente);
    }


}
