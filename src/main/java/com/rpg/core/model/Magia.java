package com.rpg.core.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Magia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_MAGIA")
    private Long idMagia;

    private String nome;
    private String descricao;
    private String duracao;

    @Column(name = "ALVO_AREA")
    private String alvoArea;

    private int custo;
    private int circulo;
    private String dano;
    private Integer ativo;

    @ManyToOne
    @JoinColumn(name = "ID_ESCOLA")
    private EscolaMagia escolaMagia;

    @ManyToOne
    @JoinColumn(name = "ID_EXECUCAO_MAGIA")
    private ExecucaoMagia execucaoMagia;

    @ManyToOne
    @JoinColumn(name = "ID_TIPO_MAGIA")
    private TipoMagia tipoMagia;

    @ManyToOne
    @JoinColumn(name = "ID_RESISTENCIA")
    private Resistencia resistencia;

    public Magia(Long idMagia) {
        this.idMagia = idMagia;
    }
}
