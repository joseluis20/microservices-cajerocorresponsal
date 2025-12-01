package com.valtxcorresponsal.cliente_service.business.api.dtos;

import lombok.Builder;
import java.util.List;

@Builder
public record MessageCuotaResponseDto (
        int statusCode,
        List<CuotaResponseDto> results,
        String error,
        String message
){
}
