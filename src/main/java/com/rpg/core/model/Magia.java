package com.rpg.core.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Magia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_MAGIA")
    private Long idMagia;

    private String nome;
    private String descricao;
    private String duracao;

    @Column(name = "ALVO_AREA")
    private String alvoArea;

    private int custo;
    private int circulo;
    private String dano;
    private Integer ativo;

    @ManyToOne
    @JoinColumn(name = "ID_ESCOLA")
    private EscolaMagia escolaMagia;

    @ManyToOne
    @JoinColumn(name = "ID_EXECUCAO_MAGIA")
    private ExecucaoMagia execucaoMagia;

    @ManyToOne
    @JoinColumn(name = "ID_TIPO_MAGIA")
    private TipoMagia tipoMagia;

    @ManyToOne
    @JoinColumn(name = "ID_RESISTENCIA")
    private Resistencia resistencia;

    // ---- IMAGEM EM BYTEA ----
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "IMAGEM")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JdbcTypeCode(SqlTypes.BINARY)
    private byte[] imagem;

    @Column(name = "IMAGEM_CONTENT_TYPE", length = 100)
    private String imagemContentType;

    @Column(name = "IMAGEM_FILENAME", length = 255)
    private String imagemFilename;

    public Magia(Long idMagia) {
        this.idMagia = idMagia;
    }
}
