package com.valtxcorresponsal.cliente_service.business.api.dtos;

import lombok.Builder;

@Builder
public record ClienteResponseDto(

        Long idClient,

        Long tipDocCli,

        String nroDocument,

        String firstName,

        String lastName,

        String email,

        String phoneNumber
) {
}
