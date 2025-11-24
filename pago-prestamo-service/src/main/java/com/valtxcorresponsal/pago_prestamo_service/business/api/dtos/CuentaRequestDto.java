package com.valtxcorresponsal.pago_prestamo_service.business.api.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record CuentaRequestDto(


        @NotNull(message = "El campo tipDocCli no puede estar vacío")
        Integer tipDocCli,

        @NotNull(message = "El campo nroDocCli no puede estar vacío")
        String nroDocCli


) {
}
