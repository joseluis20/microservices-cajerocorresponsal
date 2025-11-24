package com.valtxcorresponsal.pago_prestamo_service.business.api.dtos;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TokenValidationResponseEmail {

    private boolean value;

    private String message;

}
