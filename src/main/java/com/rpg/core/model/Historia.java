package com.rpg.core.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Historia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_HISTORIA")
    private Long idHistoria;

    private String nome;
    private String descricao;

    public Historia(Long idHistoria) {
        this.idHistoria = idHistoria;
    }
}
