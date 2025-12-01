package com.valtxcorresponsal.cliente_service.business.data.model.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "credito")
public class CreditoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cre_id")
    private Long id;

    @Column(name = "nro_credito", nullable = false, length = 50)
    private String nroCredito;

    @Column(name = "tip_credito", nullable = false, length = 50)
    private String tipoCredito;

    @Column(name = "estado", nullable = false)
    private Boolean estado;

    @Column(name = "monto", nullable = false)
    private Double monto;

    @Column(name = "saldo", nullable = false)
    private Double saldo;

    @Column(name = "nro_cuotas", nullable = false)
    private Integer nroCuotas;

    @Column(name = "cre_fec_crea")
    private LocalDateTime fechaCreacion;

    @Column(name = "cre_fec_act")
    private LocalDateTime fechaActualizacion;

    @Column(name = "cre_usr_crea")
    private String usuarioCreacion;

    @Column(name = "cre_usr_act")
    private String usuarioActualizacion;

    // --- RELACIÓN CON CLIENTE ---
    @ManyToOne
    @JoinColumn(name = "cli_id", nullable = false)
    private ClientEntity cliente;


}
