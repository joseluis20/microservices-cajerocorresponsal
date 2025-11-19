package com.valtxcorresponsal.retiro_service.business.api.dtos;

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
