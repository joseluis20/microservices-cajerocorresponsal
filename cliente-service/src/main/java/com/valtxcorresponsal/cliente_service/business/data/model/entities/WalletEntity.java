package com.valtx.corresponsal_backend.domain.model;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Wallet {

    private Long id;

    private Double balance;

    private boolean active;

    private String userCreated;
    private String userUpdated;
    private String terminalCreated;
    private String terminalUpdated;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private Currency idCurrency;

    private User user;

}
