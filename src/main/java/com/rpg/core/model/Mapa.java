package com.rpg.core.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mapa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_MAPA")
    private Long idMapa;

    @ManyToOne
    @JoinColumn(name = "ID_JOGO", nullable = false)
    private Jogo jogo;

    private String descricao;
    private String nome;
    private Integer grid;
    private Integer ativo;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @JsonIgnore // evita serializar a imagem em listar/buscar
    private byte[] imagem;

    // opcional, para responder o Content-Type correto ao baixar
    private String imagemContentType;

    public Mapa(Long idMapa) {
        this.idMapa = idMapa;
    }
}
