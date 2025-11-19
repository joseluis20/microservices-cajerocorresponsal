package com.valtxcorresponsal.cliente_service.business.api.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record ClienteRequestDto(

        @NotNull(message = "El campo tipDocCli no puede estar vacío")
        Long tipDocCli,

        @NotEmpty(message = "El campo nroDocument no puede estar vacío")
        String nroDocument


) {
}
