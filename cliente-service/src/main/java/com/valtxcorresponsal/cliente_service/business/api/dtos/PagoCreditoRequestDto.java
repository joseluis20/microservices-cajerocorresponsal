package com.valtxcorresponsal.cliente_service.business.api.dtos;

import lombok.Builder;

@Builder
public record PagoCreditoRequestDto(String nroCredito,
                                    Integer nroCuota) {
}
