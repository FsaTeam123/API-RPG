package com.rpg.core.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProeficienciaClasse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PROEFICIENCIA_PLAYER")
    private Long idProeficienciaPlayer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_CLASSE", nullable = false) // padronize o nome
    private Classe classe;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_PROEFICIENCIA", nullable = false)
    private Proeficiencia proeficiencia;

    public ProeficienciaClasse(Long id) { this.idProeficienciaPlayer = id; }
}
