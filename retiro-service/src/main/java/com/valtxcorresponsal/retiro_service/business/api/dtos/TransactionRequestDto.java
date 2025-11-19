package com.valtxcorresponsal.retiro_service.business.api.dtos;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record TransactionRequestDto(

        @NotBlank(message = "El campo token no puede estar vacío")
        String token,

        @NotNull(message = "El amount es obligatorio")
        @DecimalMin(value = "0.01", message = "El amount tiene que ser mayor a cero")
        Double amount,

        @NotNull(message = "El campo agenteId no puede estar vacío")
        Long agenteId,

        @NotNull(message = "El campo tipDocCli no puede estar vacío")
        Integer tipDocCli,

        @NotNull(message = "El campo nroDocCli no puede estar vacío")
         String nroDocCli,

        @NotNull(message = "El campo cuentaOrigen no puede estar vacío")
        String cuentaOrigen
) {
}
