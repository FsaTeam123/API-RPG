package com.rpg.core.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;

@Entity
@Table(name = "codigos_verificacao")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class CodigoVerificacao {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false) private String email;
    @Column(nullable = false) private String codigo;
    @Column(nullable = false) private Instant expiraEm;
    @Column(nullable = false) private boolean usado;
    @Column(nullable = false) private Instant criadoEm;
}
