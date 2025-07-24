package com.rpg.core.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PLAYER")
    private Long idPlayer;

    private String nome;

    private int forca;
    private int destreza;
    private int sabedoria;
    private int constituicao;
    private int inteligencia;
    private int carisma;

    private int pv;
    @Column(name = "PV_MAX")
    private int pvMax;
    @Column(name = "PV_TEMP")
    private int pvTemp;

    private int pm;
    @Column(name = "PM_MAX")
    private int pmMax;
    @Column(name = "PM_TEMP")
    private int pmTemp;

    @ManyToOne
    @JoinColumn(name = "ID_USUARIO", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "ID_ORIGEM", nullable = false)
    private Origem origem;

    @ManyToOne
    @JoinColumn(name = "ID_RACA", nullable = false)
    private Raca raca;

    @ManyToOne
    @JoinColumn(name = "ID_RIQUEZA", nullable = false)
    private Riqueza riqueza;

    @ManyToOne
    @JoinColumn(name = "ID_DIVINDADE", nullable = false)
    private Divindade divindade;

    @ManyToOne
    @JoinColumn(name = "ID_CLASSE", nullable = false)
    private Classe classe;

    @ManyToOne
    @JoinColumn(name = "ID_TAMANHO", nullable = false)
    private Tamanho tamanho;

    public Player(Long idPlayer) {
        this.idPlayer = idPlayer;
    }
}
