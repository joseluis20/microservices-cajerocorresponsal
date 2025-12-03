package com.valtxcorresponsal.pago_prestamo_service.business.api.dtos;

import lombok.Builder;

import java.util.List;

@Builder
public record MessagePagoCreditoResponseDto(
        int statusCode,
        List<PagoCreditoResponseDto> results,
        String error,
        String message
) {
}
