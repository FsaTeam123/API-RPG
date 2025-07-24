package com.rpg.core.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EscolaMagia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ESCOLA_MAGIA")
    private Long idEscolaMagia;

    private String nome;
    private String descricao;
    private Integer ativo;

    public EscolaMagia(Long idEscolaMagia) {
        this.idEscolaMagia = idEscolaMagia;
    }
}
