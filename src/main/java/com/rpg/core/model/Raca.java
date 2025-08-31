package com.rpg.core.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Raca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_RACA")
    private Long idRaca;

    private String nome;
    private String descricao;
    private Integer ativo;

    // ====== FOTO (bytea em PostgreSQL) ======
    @Lob
    @Basic(fetch = FetchType.LAZY)                 // evita puxar a foto em listagens
    @Column(name = "FOTO", columnDefinition = "bytea")
    @JsonIgnore                                    // exponha via endpoint próprio
    private byte[] foto;

    @Column(name = "FOTO_MIME", length = 100)      // ex.: image/png, image/jpeg
    private String fotoMime;

    @Column(name = "FOTO_NOME", length = 255)      // nome do arquivo (opcional)
    private String fotoNome;

    @Column(name = "FOTO_TAM")                     // tamanho em bytes (opcional)
    private Long fotoTam;

    @Column(name = "FOTO_ATUALIZADA_EM")
    private Instant fotoAtualizadaEm;
    // ========================================

    public Raca(Long idRaca) {
        this.idRaca = idRaca;
    }
}
