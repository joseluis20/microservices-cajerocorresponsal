package com.valtxcorresponsal.pago_prestamo_service.business.api.dtos;

import lombok.Builder;

@Builder
public record CuentaResponseDto(
        Long idAccount,
        String accountNumber,
        Double balance
) {
}
