package com.valtxcorresponsal.cliente_service.business.api.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenValidationRequestEmail {
    private String username;
    private String tokenNumber;
}
