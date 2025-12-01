package com.valtxcorresponsal.cliente_service.business.api.dtos;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record CuotaResponseDto(
        Integer nroCuota,
        Double montoCuota,
        Boolean estado,
        LocalDate fechaPago
) {
}
