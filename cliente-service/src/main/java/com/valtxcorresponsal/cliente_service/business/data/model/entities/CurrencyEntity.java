package com.valtxcorresponsal.cliente_service.business.data.model.entities;


import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyEntity {

    private Long id;

    private String name;

    private String description;

    private String symbol;


    private Double exchangeRate;

    private Boolean active;

    private String userCreated;

    private String userUpdated;

    private String terminalCreated;

    private String terminalUpdated;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
