package com.rpg.core.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Riqueza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_RIQUEZA")
    private Long idRiqueza;

    @Column(name = "TIBAR_OURO", nullable = false)
    private Integer tibarOuro;

    @Column(name = "TIBAR_PRATA", nullable = false)
    private Integer tibarPrata;

    @Column(name = "TIBAR_COBRE", nullable = false)
    private Integer tibarCobre;

    public Riqueza(Long idRiqueza) {
        this.idRiqueza = idRiqueza;
    }
}
