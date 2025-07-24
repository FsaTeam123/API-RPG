package com.rpg.core.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Proeficiencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PROEFICIENCIA")
    private Long idProeficiencia;

    private String nome;
    private String descricao;
    private Integer ativo;


    public Proeficiencia(Long idProeficiencia) {
        this.idProeficiencia = idProeficiencia;
    }
}
