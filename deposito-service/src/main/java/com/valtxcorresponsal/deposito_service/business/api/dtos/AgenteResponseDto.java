package com.valtxcorresponsal.deposito_service.business.api.dtos;

import lombok.Builder;

@Builder
public record AgenteResponseDto(
        Long ageId,
        String ageNom,
        String address
      //  String ageApePat,
      //  String ageApeMat
) {
}
