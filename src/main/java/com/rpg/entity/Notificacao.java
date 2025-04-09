package com.rpg.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notificacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idNotificacao;

    private String titulo;
    private String menssagem;
    private int isread;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;
}