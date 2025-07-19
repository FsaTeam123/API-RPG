package com.rpg.core.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstiloCampanha {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ESTILO_CAMPANHA")
    private Long idEstiloCampanha;

    private String nome;
    private String descricao;

    public EstiloCampanha(Long idEstiloCampanha) {
        this.idEstiloCampanha = idEstiloCampanha;
    }
}
