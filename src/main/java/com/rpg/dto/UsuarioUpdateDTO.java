package com.rpg.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioUpdateDTO {
    private String nome;
    private String nickname;
    private String senha;
    private Long idSexo;
    private Long idPerfil;
}
