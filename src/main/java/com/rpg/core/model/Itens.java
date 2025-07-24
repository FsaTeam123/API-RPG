package com.rpg.core.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Itens {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ITENS")
    private Long idItens;

    private String nome;
    private String descricao;
    private Integer preco;
    private Integer espaco;
    private Integer ativo;

    @ManyToOne
    @JoinColumn(name = "ID_ACAO", nullable = false)
    private Acao acao;

    @ManyToOne
    @JoinColumn(name = "ID_TIPO_ITEM", nullable = false)
    private TipoItem tipoItem;

    @ManyToOne
    @JoinColumn(name = "ID_CATEGORIA_ITEM", nullable = false)
    private CategoriaItem categoriaItem;

    public Itens(Long idItens) {
        this.idItens = idItens;
    }
}
