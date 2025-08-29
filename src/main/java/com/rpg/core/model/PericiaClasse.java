package com.rpg.core.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PericiaClasse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PERICIA_CLASSE")
    private Long idPericiaClasse;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_CLASSE", nullable = false)
    private Classe classe;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_PERICIA", nullable = false)
    private Pericia pericia;

    public PericiaClasse(Long idPericiaClasse) {
        this.idPericiaClasse = idPericiaClasse;
    }
}
