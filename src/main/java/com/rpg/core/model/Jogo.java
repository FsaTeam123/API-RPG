package com.rpg.core.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Jogo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_JOGO")
    private Long idJogo;

    @ManyToOne
    @JoinColumn(name = "ID_MASTER", nullable = false)
    private Usuario master;

    private String titulo;

    @Column(name = "QTD_PESSOAS")
    private Integer qtdPessoas;

    @Column(name = "IS_ESPECIFC_CLASS")
    private Integer isEspecificClass;

    @Column(name = "NIVEL_INICIAL")
    private Integer nivelInicial;

    @ManyToOne
    @JoinColumn(name = "ID_TIPO_JOGO")
    private TipoJogo tipoJogo;

    @ManyToOne
    @JoinColumn(name = "ID_GERACAO_MUNDO")
    private GeracaoMundo geracaoMundo;

    @ManyToOne
    @JoinColumn(name = "ID_ESTILO_CAMPANHA")
    private EstiloCampanha estiloCampanha;

    @ManyToOne
    @JoinColumn(name = "ID_HISTORIA")
    private Historia historia;

    @ManyToOne
    @JoinColumn(name = "ID_TEMA")
    private Tema tema;

    private String senha;

    @Column(name = "DTC_CRIACAO")
    private LocalDateTime dataCriacao;

    private Integer ativo;

    public Jogo(Long idJogo) {
        this.idJogo = idJogo;
    }
}
