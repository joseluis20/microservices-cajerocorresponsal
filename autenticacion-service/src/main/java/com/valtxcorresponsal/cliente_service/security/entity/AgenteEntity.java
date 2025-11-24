package com.valtxcorresponsal.cliente_service.security.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "agente")
public class AgenteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="age_id")
    private Long ageId;

    @Column(name="age_tipper")
    private Integer ageTipPerso;

    @Column(name="age_tipdoc")
    private Integer ageTipDoc;

    @Column(name="age_nom")
    private String ageNom;

   // @Column(name = "Age_TipDoc")
   // @Enumerated(EnumType.STRING)
   // private TypeDocument ageTipDoc;

    @Column(name = "age_apepat")
    private String ageApePat;

    @Column(name = "age_apemat")
    private String ageApeMat;

   // @Column(name = "Age_SalAge")
   // private Double ageSalAge;

    @Column(name = "age_dir")
    private String address;

    @Column(name = "age_cel1")
    private String ageCel;

    @Column(name="age_salage")
    private Double ageSaldo;

    @Column(name = "age_esteval")
    private Integer active;



}
