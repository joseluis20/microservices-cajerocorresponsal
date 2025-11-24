package com.valtxcorresponsal.pago_prestamo_service.business.api.dtos;

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
