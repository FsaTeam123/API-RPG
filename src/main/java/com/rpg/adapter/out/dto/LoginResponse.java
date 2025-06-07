package com.rpg.adapter.out.dto;

import com.rpg.core.model.Usuario;
import lombok.Getter;

@Getter
public class LoginResponse {
    private final Long idUsuario;
    private final String nome;
    private final String email;
    private final String token;
    private final String status;

    public LoginResponse(Usuario usuario, String token, String status) {
        this.idUsuario = usuario.getIdUsuario();
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.token = token;
        this.status = status;
    }
}
