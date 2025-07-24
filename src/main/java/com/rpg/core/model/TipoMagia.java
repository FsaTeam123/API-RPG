package com.rpg.core.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TipoMagia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TIPO_MAGIA")
    private Long idTipoMagia;

    private String nome;
    private String descricao;
    private Integer ativo;

    public TipoMagia(Long idTipoMagia) {
        this.idTipoMagia = idTipoMagia;
    }
}
