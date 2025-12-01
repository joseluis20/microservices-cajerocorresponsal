package com.valtxcorresponsal.cliente_service.business.api.dtos;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MessageCreditoResponseDto {
    private Integer statusCode;
    private List<String> results;
    private String error;
    private String message;
}
