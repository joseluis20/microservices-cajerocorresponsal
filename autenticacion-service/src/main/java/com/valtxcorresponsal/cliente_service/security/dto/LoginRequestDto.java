package com.valtxcorresponsal.cliente_service.security.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record LoginRequestDto(

        @NotNull(message = "El codAgente es obligatorio")
        Long codAgente,
    @NotEmpty(message = "El userName es obligatorio")
    String userName,
    @NotEmpty(message = "El password es obligatorio")
    String password


) {
}
