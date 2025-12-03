package com.valtxcorresponsal.pago_prestamo_service.business.domain.services;

import com.valtxcorresponsal.pago_prestamo_service.business.api.dtos.PagoCreditoRequestDto;
import com.valtxcorresponsal.pago_prestamo_service.business.api.dtos.PagoCreditoResponseDto;

public interface PagoCreditoService {

    PagoCreditoResponseDto pagarCuota(PagoCreditoRequestDto request);
}
