package com.rpg.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Perfil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPerfil;

    private String nome;
    private String descricao;

    public Perfil(Long idPerfil) {
        this.idPerfil = idPerfil;
    }
}