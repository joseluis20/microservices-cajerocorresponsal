package com.valtxcorresponsal.cliente_service.business.data.model.entities;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cliente")
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cli_id")
    private Long idClient;

    @Column(name = "cli_nom")
    private String firstName;

    @Column(name = "cli_apell")
    private String lastName;

    @OneToOne
    @JoinColumn(name = "cli_tip_doc", referencedColumnName = "tip_doc_id")
    private TypeDocumentEntity typeDocument;

    @Column(name = "cli_nro_doc", unique = true, nullable = false)
    private String nroDocument;


    @Column(name = "cli_email")
    private String email;

    @Column(name = "cli_tel")
    private String phoneNumber;

    @Column(name = "cli_activo")
    private boolean active;

    @Column(name = "cli_usr_crea")
    private String userCreated;

    @Column(name = "cli_usr_act")
    private String userActivo;

    @Column(name = "cli_term_crea")
    private String terminalCreated;

    @Column(name = "cli_term_act")
    private String terminalUpdated;

    @Column(name = "cli_fec_crea")
    private LocalDateTime createdAt;

    @Column(name = "cli_fec_act")
    private LocalDateTime updatedAt;

}
