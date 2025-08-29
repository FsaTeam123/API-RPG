package com.rpg.core.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Classe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CLASSE")
    private Long idClasse;

    private String nome;
    private String descricao;

    @Column(name = "PV_INIT", nullable = false)
    private Integer pvInit;

    @Column(name = "PV_NIVEL", nullable = false)
    private Integer pvNivel;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_ATRIBUTO_PV", nullable = false)
    private Atributo atributoPV;

    @Column(name = "PM_INIT", nullable = false)
    private Integer pmInit;

    @Column(name = "PM_NIVEL", nullable = false)
    private Integer pmNivel;

    @OneToMany(mappedBy = "classe",
            cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE },
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<ProeficienciaClasse> proeficienciasClasse = new ArrayList<>();

    @OneToMany(mappedBy = "classe",
            cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE },
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    @ToString.Exclude @EqualsAndHashCode.Exclude
    private List<PericiaClasse> periciasClasse = new ArrayList<>();

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

    private Integer ativo;

    public Classe(Long idClasse) { this.idClasse = idClasse; }
}
