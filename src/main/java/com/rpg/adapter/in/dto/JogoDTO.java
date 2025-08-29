package com.rpg.adapter.in.dto;

import com.rpg.core.model.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JogoDTO {
    private Long idJogo;
    private UsuarioDTO master;
    private String titulo;
    private Integer qtdPessoas;
    private Integer isEspecificClass;
    private Integer nivelInicial;
    private TipoJogo tipoJogo;
    private GeracaoMundo geracaoMundo;
    private EstiloCampanha estiloCampanha;
    private Historia historia;
    private Tema tema;
    private String senha;
    private java.time.LocalDateTime dataCriacao;
    private Integer ativo;
}