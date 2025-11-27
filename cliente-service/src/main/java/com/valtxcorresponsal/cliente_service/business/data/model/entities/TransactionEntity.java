package com.valtxcorresponsal.cliente_service.business.data.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "transacciones")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trn_id")
    private Long id;

    @Column(name = "trn_monto", nullable = false)
    private Double amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wal_id", nullable = false)
    private WalletEntity wallet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trn_tipo_id", nullable = false)
    private TransactionTypeEntity transactionType;

    @Column(name = "trn_cta_from")
    private String accountOrigin;

    @Column(name = "trn_cta_to")
    private String accountDestination;

    @Column(name = "trn_usr_crea")
    private String userCreated;

    @Column(name = "trn_usr_act")
    private String userUpdated;

    @Column(name = "trn_term_crea")
    private String terminalCreated;

    @Column(name = "trn_term_act")
    private String terminalUpdated;

    @Column(name = "trn_fec_crea")
    private LocalDateTime createdAt;

    @Column(name = "trn_fec_act")
    private LocalDateTime updatedAt;

    @Column(name = "trn_activo")
    private boolean active;

    @Column(name = "trn_nro_operacion", unique = true)
    private String operationNumber;

    @Column(name = "trn_anio")
    private Integer year;

    @Column(name = "trn_mes")
    private Integer month;

    @Column(name = "trn_dia")
    private Integer day;
}
