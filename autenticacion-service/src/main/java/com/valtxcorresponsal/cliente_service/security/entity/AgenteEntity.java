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

    @Column(name="age_tip_per")
    private Integer ageTipPerso;

    @Column(name="age_tip_doc")
    private Integer ageTipDoc;

    @Column(name="age_nom")
    private String ageNom;

   // @Column(name = "Age_TipDoc")
   // @Enumerated(EnumType.STRING)
   // private TypeDocument ageTipDoc;

    @Column(name = "age_ape_pat")
    private String ageApePat;

    @Column(name = "age_ape_mat")
    private String ageApeMat;

   // @Column(name = "Age_SalAge")
   // private Double ageSalAge;

    @Column(name = "age_dire")
    private String address;

    @Column(name = "age_nro_cel")
    private String ageCel;

    @Column(name="age_sal_age")
    private Double ageSaldo;

    @Column(name = "age_est_age")
    private Integer active;

    @Column(name = "age_usr_crea")
    private String userCreated;

    @Column(name = "age_usr_act")
    private String userUpdated;


    @Column(name = "age_fec_crea")
    private LocalDateTime createdAt;

    @Column(name = "age_fec_act")
    private LocalDateTime updatedAt;

}
