package com.valtxcorresponsal.pago_prestamo_service.business.api.dtos;

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
