package com.rpg.core.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GeracaoMundo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_GERACAO_MUNDO")
    private Long idGeracaoMundo;

    private String nome;
    private String descricao;
    private Integer ativo;

    public GeracaoMundo(Long idGeracaoMundo) {
        this.idGeracaoMundo = idGeracaoMundo;
    }
}
