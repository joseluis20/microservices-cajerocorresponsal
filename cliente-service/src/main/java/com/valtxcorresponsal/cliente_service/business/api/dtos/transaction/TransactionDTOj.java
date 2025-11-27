package com.valtx.corresponsal_backend.infrastructure.rest.dto.transactionsdtos;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTOj {
    private String message;
    private Long idTransaction;
    private String transactionType;
    private String email;
}
