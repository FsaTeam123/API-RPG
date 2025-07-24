package com.rpg.core.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Modificador {

    @EmbeddedId
    private ModificadorId id;

    @ManyToOne
    @MapsId("idRaca")
    @JoinColumn(name = "ID_RACA")
    private Raca raca;

    @ManyToOne
    @MapsId("idAtributo")
    @JoinColumn(name = "ID_ATRIBUTO")
    private Atributo atributo;

    @Column(nullable = false)
    private String tipo;

    @Column(nullable = false)
    private Integer valor;

    @Column(name = "MAX", nullable = false)
    private Integer max;

    private Integer ativo;
}
