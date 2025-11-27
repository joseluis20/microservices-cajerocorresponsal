package com.valtxcorresponsal.cliente_service.security.dto;

import lombok.Builder;

@Builder
public record LoginResponseDto(String token,
                               Long ageId,
                               String userName,
                               String ageNom,
                               String ageApePat,
                               String ageApeMat,
                               Double ageSaldo,
                               String ageNomAge,
                               String ageCel,
                               String ageCel2,
                               String ageTelf,
                               String ageTelf2,
                               String ageCor) {

}
