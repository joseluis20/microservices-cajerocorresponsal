package com.valtxcorresponsal.cliente_service.business.data.model.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "wallet")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WalletEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double balance;

    private boolean active;

    @Column(name = "user_created")
    private String userCreated;

    @Column(name = "user_updated")
    private String userUpdated;

    @Column(name = "terminal_created")
    private String terminalCreated;

    @Column(name = "terminal_updated")
    private String terminalUpdated;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
