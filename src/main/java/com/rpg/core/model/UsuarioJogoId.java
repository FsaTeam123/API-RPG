package com.rpg.core.model;

import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioJogoId implements Serializable {
    private Long jogo;
    private Long usuario;

    // equals() e hashCode() são gerados por @Data
}
