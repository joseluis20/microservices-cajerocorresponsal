package com.valtxcorresponsal.deposito_service.business.api.dtos;

import lombok.Builder;

@Builder
public record CuentaResponseDto(
        Long idAccount,
        String accountNumber,
        Double balance
) {
}
