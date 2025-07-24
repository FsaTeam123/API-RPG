package com.rpg.core.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TipoArma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TIPO_ARMA")
    private Long idTipoArma;

    private String nome;
    private String descricao;
    private Integer ativo;

    public TipoArma(Long idTipoArma) {
        this.idTipoArma = idTipoArma;
    }
}
