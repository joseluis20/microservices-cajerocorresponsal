package com.valtxcorresponsal.autenticacion_service.security.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "perfil")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "per_cod")
    private Long idPerfil;

    @Column(name = "per_age_cod")
    private Long codAgente;

	@Column(name = "per_nom")
	private String userName;

	@Column(name = "per_con")
	private String password;

	@Column(name = "per_rol")
	private String rol;

    @Column(name = "per_est")
    private Integer estado;

    @Column(name = "per_est_act")
    private LocalDateTime fecActiva;

    @Column(name = "per_fec_mod")
    private LocalDateTime fecModifica;


}
