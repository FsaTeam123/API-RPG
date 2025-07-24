package com.rpg.core.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TipoDano {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TIPO_DANO")
    private Long idTipoDano;

    private String nome;
    private String descricao;
    private Integer ativo;

    public TipoDano(Long idTipoDano) {
        this.idTipoDano = idTipoDano;
    }
}
