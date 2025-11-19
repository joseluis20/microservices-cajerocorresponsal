package com.valtxcorresponsal.retiro_service.business.api.dtos;

import lombok.Builder;

@Builder
public record TransactionResponseDto(

        Long id,

        Double amount,

        Long operationNumber,

        Integer codAgente,

        String nroDocCli

) {
}
