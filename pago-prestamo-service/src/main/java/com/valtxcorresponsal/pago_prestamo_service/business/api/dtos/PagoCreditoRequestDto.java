package com.valtxcorresponsal.pago_prestamo_service.business.api.dtos;

import lombok.Builder;

@Builder
public record PagoCreditoRequestDto(String codPrest,
                                    Integer nroCuota) {
}
