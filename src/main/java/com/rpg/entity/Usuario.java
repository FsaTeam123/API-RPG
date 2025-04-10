package com.rpg.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    private String nome;
    private String nickname;

    @ManyToOne
    @JoinColumn(name = "idSexo")
    private Sexo sexo;

    private String senha;

    @Column(unique = true, nullable = false)
    private String email;


    @ManyToOne
    @JoinColumn(name = "idPerfil")
    private Perfil perfil;

    private LocalDateTime dtcCriacao;
}