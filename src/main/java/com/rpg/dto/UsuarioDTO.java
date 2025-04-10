package com.rpg.dto;

import com.rpg.entity.Perfil;
import com.rpg.entity.Sexo;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class UsuarioDTO {
    private Long idUsuario;
    private String nome;
    private String nickname;
    private String email;
    private Sexo sexo;
    private Perfil perfil;
    private LocalDateTime dtcCriacao;
}
