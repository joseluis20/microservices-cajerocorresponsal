package com.valtxcorresponsal.cliente_service.business.data.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cuenta" , schema = "clientes")
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="cue_id")
    private Long idAccount;

    @Column(name="cue_numero", unique = true, nullable = false)
    private String accountNumber;

    @Column(name = "cue_tip_cue")
    @Enumerated(EnumType.STRING)
    private TypeAccount typeAccount;

    @Column(name = "cue_saldo")
    private Double balance;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cli_id", nullable = false)
    private ClientEntity client;

    @Column(name = "cue_activo")
    private boolean active;

    @Column(name = "cue_usr_crea")
    private String userCreated;

    @Column(name = "cue_usr_act")
    private String userUpdated;

    @Column(name = "cue_term_crea")
    private String terminalCreated;

    @Column(name = "cue_term_act")
    private String terminalUpdated;

    @Column(name = "cue_fec_crea")
    private LocalDateTime createdAt;

    @Column(name = "cue_fec_act")
    private LocalDateTime updatedAt;

    @Column(name="cue_cci", unique = true, nullable = true)
    private String cciNumber;

}
