package com.rpg.core.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Anotacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ANOTACAO")
    private Long idAnotacao;

    // <- É ISSO que vai no JSON e na INSERT/UPDATE
    @Column(name = "ID_JOGO", nullable = false)
    private Long jogoId;

    // Associação só para navegar, NÃO participa de insert/update do FK
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_JOGO", insertable = false, updatable = false)
    @JsonIgnore
    private Jogo jogo;

    @Column(name = "ANOTACAO", nullable = false, columnDefinition = "TEXT")
    private String anotacao;
}
