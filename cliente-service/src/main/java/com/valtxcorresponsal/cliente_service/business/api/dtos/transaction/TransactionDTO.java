package com.valtxcorresponsal.cliente_service.business.api.dtos.transaction;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTO {
    private String message;
    private Long idTransaction;
    private String transactionType;
    private String email;
}
