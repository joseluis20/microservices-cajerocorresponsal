package com.valtxcorresponsal.cliente_service.business.api.dtos;

import lombok.Builder;

import java.util.List;

@Builder
public record MessageClienteResponseDto(
        int statusCode,
        List<ClienteResponseDto> results,
        String error,
        String message
) {
}
