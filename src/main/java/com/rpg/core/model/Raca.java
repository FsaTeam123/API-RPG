package com.rpg.core.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "RACA")
@Data @NoArgsConstructor @AllArgsConstructor
public class Raca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_RACA")
    private Long idRaca;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "DESCRICAO")
    private String descricao;

    @Column(name = "ATIVO")
    private Integer ativo;

    // ====== FOTO (bytea em PostgreSQL) ======
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "FOTO", columnDefinition = "bytea")
    @JdbcTypeCode(org.hibernate.type.SqlTypes.BINARY)   // <- igual em Classe.imagem
    @JsonIgnore
    private byte[] foto;

    @Column(name = "FOTO_MIME", length = 100)
    private String fotoMime;

    @Column(name = "FOTO_NOME", length = 255)
    private String fotoNome;

    @Column(name = "FOTO_TAM")
    private Long fotoTam;

    @Column(name = "FOTO_ATUALIZADA_EM")
    private Instant fotoAtualizadaEm;
    // ========================================

    // <<<<<<<<<<<<<< NOVO: habilidades da raça >>>>>>>>>>>>>>
    @OneToMany(
            mappedBy = "raca",
            cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE },
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    @JsonManagedReference("raca-habs")
    @ToString.Exclude @EqualsAndHashCode.Exclude
    private List<HabilidadeRaca> habilidades = new ArrayList<>();
    // =======================================================

    public Raca(Long idRaca) { this.idRaca = idRaca; }

    // Helpers para manter os dois lados sincronizados
    public void addHabilidade(HabilidadeRaca h) {
        if (h == null) return;
        h.setRaca(this);
        habilidades.add(h);
    }
    public void removeHabilidade(Long idHab) {
        habilidades.removeIf(h -> {
            if (Objects.equals(h.getId(), idHab)) {
                h.setRaca(null);
                return true;
            }
            return false;
        });
    }
}
