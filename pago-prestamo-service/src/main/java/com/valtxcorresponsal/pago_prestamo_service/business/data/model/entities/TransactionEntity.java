package com.valtxcorresponsal.pago_prestamo_service.business.data.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Entity
@Table(name = "transacciones")
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trn_id")
    private Long id; //Antes era Integer

    @Positive(message = "El monto de la transaccion debe ser mayor a cero")
    @Column(name = "trn_monto")
    private Double amount;

    @Column(name = "trn_cod_age")
    private Long codAgente;

    @Column(name = "trn_nro_cel")
    private String numCel;

    @Column(name = "trn_tip_doc")
    private Integer tipDocCli;

    @Column(name = "trn_nro_doc")
    private String nroDocCli;

    @Column(name = "trn_tipo_oper")
    private String tipoOperacion;

    @Column(name = "trn_fecha")
    private LocalDateTime fecTransaccion;

    @Column(name = "trn_cta_from")
    private String cuentaOrigen;

    @Column(name = "trn_cta_to")
    private String cuentaDestino;

    @Column(name = "trn_nro_operacion", unique = true)
    private Long operationNumber;

    @Column(name = "nro_credito")
    private String nroCredito;

    @Column(name = "nro_cuota")
    private Integer nroCuota;




}
