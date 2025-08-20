package com.rpg.adapter.out.dto;

import com.rpg.core.model.Usuario;
import lombok.Getter;

@Getter
public class LoginResponse {
    private final Long idUsuario;
    private final Long idPerfil;
    private final String nome;
    private final String nickname;
    private final String email;
    private final String token;
    private final String status;
    private final String fotoUrl;
    private final Integer ativo;

    public LoginResponse(Usuario usuario, String token, String status, String fotoUrl) {
        this.idUsuario = usuario.getIdUsuario();
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.token = token;
        this.status = status;
        this.fotoUrl = fotoUrl;
        this.nickname = usuario.getNickname();
        this.ativo = usuario.getAtivo();
        this.idPerfil = usuario.getPerfil().getIdPerfil();
    }
}

