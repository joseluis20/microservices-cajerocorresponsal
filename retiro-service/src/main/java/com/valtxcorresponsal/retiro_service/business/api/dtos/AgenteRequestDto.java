package com.valtxcorresponsal.retiro_service.business.api.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

@Builder
public record AgenteRequestDto(
        @NotEmpty(message = "El campo ageNom no puede estar vacío")
         String ageNom,
      //  @NotEmpty(message = "El campo ageTipDoc no puede estar vacío")
      //   String ageTipDoc,
        @NotEmpty(message = "El campo ageApePat no puede estar vacío")
         String ageApePat,
        @NotEmpty(message = "El campo ageApeMat no puede estar vacío")
         String ageApeMat
) {
}
