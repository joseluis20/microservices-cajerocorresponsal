package com.valtxcorresponsal.cliente_service.business.api.dtos;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccessRecoveryResponse {

    private String message;

    private String accessRecoveryCode;

}
