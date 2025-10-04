package com.rpg.core.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Poder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PODER")
    private Long idPoder;

    private String nome;
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "ID_TIPO_PODER", nullable = false)
    private TipoPoder tipoPoder;

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

    public Poder(Long idPoder) {
        this.idPoder = idPoder;
    }
}
