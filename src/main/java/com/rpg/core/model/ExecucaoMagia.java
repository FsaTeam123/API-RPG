package com.rpg.core.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExecucaoMagia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_EXECUCAO_MAGIA")
    private Long idExecucaoMagia;

    private String nome;
    private String descricao;
    private Integer ativo;

    public ExecucaoMagia(Long idExecucaoMagia) {
        this.idExecucaoMagia = idExecucaoMagia;
    }
}
