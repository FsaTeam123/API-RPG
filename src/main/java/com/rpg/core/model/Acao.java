package com.rpg.core.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Acao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ACAO")
    private Long idAcao;

    private String nome;
    private String descricao;
    private Integer ativo;

    public Acao(Long idAcao) {
        this.idAcao = idAcao;
    }
}
