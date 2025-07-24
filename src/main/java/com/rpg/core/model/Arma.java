package com.rpg.core.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Arma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ARMA")
    private Long idArma;

    private String nome;
    private String descricao;
    private String dano;
    private String critico;
    private String alcance;
    private String preco;
    private String tipo;
    private Integer ativo;
    private Integer atibuto;
    private Integer multiplicadrCritico;
    private Integer margemAmeca;
    private Integer espacos;

    @ManyToOne
    @JoinColumn(name = "ID_TIPO_DANO")
    private TipoDano tipoDano;

    @ManyToOne
    @JoinColumn(name = "ID_TIPO_ARMA")
    private TipoArma tipoArma;

    public Arma(Long idArma) {
        this.idArma = idArma;
    }
}
