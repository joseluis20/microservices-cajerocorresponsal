package com.valtxcorresponsal.cliente_service.business.api.dtos;

    import jakarta.validation.constraints.NotBlank;
    import jakarta.validation.constraints.NotNull;
    import lombok.Builder;

@Builder
    public record CuentaRequestDto(
            String token,

            @NotNull(message = "El campo tipDocCli no puede estar vacío")
            Long tipDocCli,

            @NotNull(message = "El campo nroDocCli no puede estar vacío")
            String nroDocCli
    ) {
    }
