package com.rpg.core.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Classe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CLASSE")
    private Long idClasse;

    private String nome;
    private String descricao;

    @Column(name = "PV_INIT", nullable = false)
    private Integer pvInit;

    @Column(name = "PV_NIVEL", nullable = false)
    private Integer pvNivel;

    @ManyToOne
    @JoinColumn(name = "ID_ATRIBUTO_PV", nullable = false)
    private Atributo atributoPV;

    @Column(name = "PM_INIT", nullable = false)
    private Integer pmInit;

    @Column(name = "PM_NIVEL", nullable = false)
    private Integer pmNivel;

    @ManyToOne
    @JoinColumn(name = "ID_PROEFICIENCIA", nullable = false)
    private Proeficiencia proeficiencia;

    private Integer ativo;

    public Classe(Long idClasse) {
        this.idClasse = idClasse;
    }
}
