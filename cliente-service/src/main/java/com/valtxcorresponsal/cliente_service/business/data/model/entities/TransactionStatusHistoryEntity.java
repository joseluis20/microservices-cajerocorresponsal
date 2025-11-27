package com.valtxcorresponsal.cliente_service.business.data.model.entities;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "transaction_status_history")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionStatusHistoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tsh_id")
    private Long idTransactionStatusHistory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trn_id", nullable = false)
    private TransactionEntity transaction;

    @Column(name = "tsh_status", nullable = false, length = 50)
    private String status;

    @Column(name = "tsh_fec_crea", nullable = false)
    private LocalDateTime createdAt;
}
