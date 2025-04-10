package com.rpg.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioCreateDTO {

    @NotBlank(message = "Nome obrigatório")
    private String nome;

    @NotBlank(message = "Nickname obrigatório")
    private String nickname;

    @NotBlank(message = "Senha obrigatória")
    private String senha;

    @Email(message = "Email inválido")
    @NotBlank(message = "Email obrigatório")
    private String email;

    private Long idSexo;
    private Long idPerfil;
}
