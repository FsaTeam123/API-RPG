package com.rpg.core.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Divindade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_DIVINDADE")
    private Long idDivindade;

    private String nome;
    private String descricao;
    private Integer ativo;

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

    public Divindade(Long idDivindade) {
        this.idDivindade = idDivindade;
    }
}
