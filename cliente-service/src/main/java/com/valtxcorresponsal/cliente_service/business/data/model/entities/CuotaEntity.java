package com.valtxcorresponsal.cliente_service.business.data.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cuota")
public class CuotaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cuota_id")
    private Long id;

    @Column(name = "nro_cuota", nullable = false)
    private Integer nroCuota;

    @Column(name = "saldo", nullable = false)
    private Double saldo;

    @Column(name = "capital", nullable = false)
    private Double capital;

    @Column(name = "interes", nullable = false)
    private Double interes;

    @Column(name = "monto_total", nullable = false)
    private Double montoTotal;

    @Column(name = "estado", nullable = false)
    private Boolean estado;

    // ---------------------------
    // NUEVOS CAMPOS
    // ---------------------------
    @Column(name = "fecha_vencimiento", nullable = false)
    private LocalDate fechaVencimiento;

    @Column(name = "fecha_pago")
    private LocalDate fechaPago;

    // ---------------------------
    // METADATOS BASE
    // ---------------------------
    @Column(name = "cuota_fec_crea")
    private LocalDateTime fechaCreacion;

    @Column(name = "cuota_fec_act")
    private LocalDateTime fechaActualizacion;

    @Column(name = "cuota_usr_crea")
    private String usuarioCreacion;

    @Column(name = "cuota_usr_act")
    private String usuarioActualizacion;

    // ---------------------------
    // RELACIÓN: MUCHOS A UNO
    // ---------------------------
    @ManyToOne
    @JoinColumn(name = "nro_credito", referencedColumnName = "nro_credito")
    private CreditoEntity credito;


}
