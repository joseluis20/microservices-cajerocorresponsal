package com.valtxcorresponsal.pago_prestamo_service.business.api.dtos;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record TransactionRequestDto(


        @NotNull(message = "El campo nroCredito no puede estar vacío")
        String nroCredito,

        @NotNull(message = "El campo nroCuota no puede estar vacío")
        Integer nroCuota


) {
}
