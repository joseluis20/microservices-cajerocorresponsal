package com.valtxcorresponsal.cliente_service.security.dto;

import lombok.Builder;

@Builder
public record LoginResponseDto(String token,String ageNom,String ageApePat,String ageApeMat,Double ageSaldo) {

}
