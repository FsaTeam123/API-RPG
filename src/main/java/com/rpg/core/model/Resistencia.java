package com.rpg.core.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Resistencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_RESISTENCIA")
    private Long idResistencia;

    private String nome;
    private String descricao;
    private Integer ativo;

    public Resistencia(Long idResistencia) {
        this.idResistencia = idResistencia;
    }
}
