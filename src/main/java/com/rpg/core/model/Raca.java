package com.rpg.core.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Raca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_RACA")
    private Long idRaca;

    private String nome;
    private String descricao;
    private Integer ativo;

    public Raca(Long idRaca) {
        this.idRaca = idRaca;
    }
}
