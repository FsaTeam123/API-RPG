package com.rpg.core.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Aprimoramento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_APRIMORAMENTO")
    private Long idAprimoramento;

    private String nome;
    private String descricao;
    private String dano;
    private int circulo;

    @ManyToOne
    @JoinColumn(name = "ID_MAGIA", nullable = false)
    private Magia magia;

    public Aprimoramento(Long idAprimoramento) {
        this.idAprimoramento = idAprimoramento;
    }
}
