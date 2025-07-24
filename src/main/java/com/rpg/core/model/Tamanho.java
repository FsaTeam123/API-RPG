package com.rpg.core.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tamanho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TAMANHO")
    private Long idTamanho;

    private String nome;
    private Integer deslocamento;
    private String descricao;

    @Column(name = "MOD_FUTIVI")
    private Integer modFutivi;

    @Column(name = "MOD_MANOBRA")
    private Integer modManobra;

    private Integer ativo;

    public Tamanho(Long idTamanho) {
        this.idTamanho = idTamanho;
    }
}
