package com.valtxcorresponsal.cliente_service.business.api.dtos;

import lombok.Builder;

@Builder
public record PagoCreditoRequestDto(String codPrest,
                                    Integer nroCuota) {
}
