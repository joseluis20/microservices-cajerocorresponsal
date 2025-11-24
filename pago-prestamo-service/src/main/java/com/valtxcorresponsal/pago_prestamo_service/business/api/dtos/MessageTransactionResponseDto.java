package com.valtxcorresponsal.pago_prestamo_service.business.api.dtos;

import lombok.Builder;

import java.util.List;

@Builder
public record MessageTransactionResponseDto(
        int statusCode,
        List<TransactionResponseDto> results,
        String error,
        String message
) {
}
