package com.valtxcorresponsal.deposito_service.business.api.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

@Builder
public record LoginRequestDto(
    @NotEmpty(message = "El userName es obligatorio")
    String userName,
    @NotEmpty(message = "El password es obligatorio")
    String password
) {
}
