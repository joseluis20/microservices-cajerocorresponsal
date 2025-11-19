package com.valtxcorresponsal.cliente_service.business.api.dtos;

import lombok.Builder;
import com.valtxcorresponsal.cliente_service.business.data.model.entities.TypeAccount;

@Builder
public record CuentaResponseDto(
        Long idAccount,
        String accountNumber,
        TypeAccount typeAccount,
        Double balance
) {
}
