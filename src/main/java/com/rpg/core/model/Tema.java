package com.rpg.core.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TEMA")
    private Long idTema;

    private String nome;
    private String descricao;
    private Integer ativo;

    public Tema(Long idTema) {
        this.idTema = idTema;
    }
}
