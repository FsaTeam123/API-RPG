package com.rpg.core.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "foto_usuario")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class FotoUsuario {

    @Id
    @Column(name = "id_usuario")               // <-- PK é id_usuario
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId                                     // <-- compartilha a PK com Usuario
    @JoinColumn(name = "id_usuario")            // <-- FK na mesma coluna
    private Usuario usuario;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    // Em Postgres use BYTEA:
    // @Column(columnDefinition = "BYTEA")
    private byte[] dados;

    @Column(name = "content_type", length = 200)
    private String contentType;

    @Column(name = "nome_arquivo", length = 255)
    private String nomeArquivo;

    @Column(name = "tamanho_bytes")
    private Long tamanhoBytes;

    @Column(name = "atualizado_em")
    private LocalDateTime atualizadoEm;
}
