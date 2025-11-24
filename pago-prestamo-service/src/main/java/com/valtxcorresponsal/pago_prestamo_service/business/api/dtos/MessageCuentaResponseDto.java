package com.valtxcorresponsal.pago_prestamo_service.business.api.dtos;

import lombok.Builder;

import java.util.List;

@Builder
public record MessageCuentaResponseDto(
        int statusCode,
        List<CuentaResponseDto> results,
        String error,
        String message
) {
}
