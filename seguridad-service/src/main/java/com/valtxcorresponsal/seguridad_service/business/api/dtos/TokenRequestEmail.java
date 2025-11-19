package com.valtxcorresponsal.seguridad_service.business.api.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenRequestEmail {

    private String username;
    private String email;

}
