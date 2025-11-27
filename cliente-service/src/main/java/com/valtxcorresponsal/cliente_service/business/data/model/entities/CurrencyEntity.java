package com.valtx.corresponsal_backend.domain.model;


import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Currency {

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
