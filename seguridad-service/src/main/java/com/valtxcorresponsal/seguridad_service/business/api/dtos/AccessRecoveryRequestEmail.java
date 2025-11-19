package com.valtxcorresponsal.seguridad_service.business.api.dtos;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccessRecoveryRequestEmail {

    private String nroDocument;

    private String email;

}
