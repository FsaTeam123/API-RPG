package com.rpg.core.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "HABILIDADE_RACA")
@Data @NoArgsConstructor @AllArgsConstructor
public class HabilidadeRaca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_HAB_RACA")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_RACA", nullable = false)
    @JsonBackReference("raca-habs")           // evita loop no JSON
    @ToString.Exclude @EqualsAndHashCode.Exclude
    private Raca raca;

    @Column(name = "NOME", nullable = false, length = 120)
    private String nome;

    @Column(name = "DESCRICAO", columnDefinition = "text")
    private String descricao;

    @Column(name = "ATIVO")
    private Integer ativo;
}
