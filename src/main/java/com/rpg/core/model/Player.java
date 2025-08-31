package com.rpg.core.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "PLAYER",
        indexes = {
                @Index(name = "IX_PLAYER_USUARIO", columnList = "ID_USUARIO"),
                @Index(name = "IX_PLAYER_NOME",    columnList = "NOME")
        })
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PLAYER")
    @EqualsAndHashCode.Include
    private Long idPlayer;

    @Column(name = "NOME")
    private String nome;

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

    // Atributos do personagem (mantidos)
    private int forca;
    private int destreza;
    private int sabedoria;
    private int constituicao;
    private int inteligencia;
    private int carisma;

    private int pv;
    @Column(name = "PV_MAX")  private int pvMax;
    @Column(name = "PV_TEMP") private int pvTemp;

    private int pm;
    @Column(name = "PM_MAX")  private int pmMax;
    @Column(name = "PM_TEMP") private int pmTemp;

    // Relações existentes (LAZY)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_USUARIO", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_JOGO", nullable = false)
    private Jogo jogo;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_ORIGEM", nullable = false)
    private Origem origem;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_RACA", nullable = false)
    private Raca raca;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_RIQUEZA", nullable = false)
    private Riqueza riqueza;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_DIVINDADE", nullable = false)
    private Divindade divindade;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_CLASSE", nullable = false)
    private Classe classe;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_TAMANHO", nullable = false)
    private Tamanho tamanho;

    // Coleções do personagem
    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<MagiaPlayer> magias = new HashSet<>();

    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<PoderPlayer> poderes = new HashSet<>();

    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<PericiaPlayer> pericias = new HashSet<>();

    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<ArmaPlayer> armas = new HashSet<>();

    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<BolsaPlayer> bolsa = new HashSet<>();

    public Player(Long idPlayer) { this.idPlayer = idPlayer; }

    /* ===== Helpers (mantidos) ===== */
    public void addMagia(Magia magia) {
        MagiaPlayer mp = new MagiaPlayer();
        mp.setPlayer(this);
        mp.setMagia(magia);
        this.magias.add(mp);
    }
    public void removeMagia(Magia magia) {
        this.magias.removeIf(mp -> mp.getMagia().getIdMagia().equals(magia.getIdMagia()));
    }
    public void addPoder(Poder poder) {
        PoderPlayer pp = new PoderPlayer();
        pp.setPlayer(this);
        pp.setPoder(poder);
        this.poderes.add(pp);
    }
    public void removePoder(Poder poder) {
        this.poderes.removeIf(pp -> pp.getPoder().getIdPoder().equals(poder.getIdPoder()));
    }
    public void addPericia(Pericia pericia) {
        PericiaPlayer pr = new PericiaPlayer();
        pr.setPlayer(this);
        pr.setPericia(pericia);
        this.pericias.add(pr);
    }
    public void removePericia(Pericia pericia) {
        this.pericias.removeIf(pr -> pr.getPericia().getIdPericia().equals(pericia.getIdPericia()));
    }
    public void addArma(Arma arma) {
        ArmaPlayer ap = new ArmaPlayer();
        ap.setPlayer(this);
        ap.setArma(arma);
        this.armas.add(ap);
    }
    public void removeArma(Arma arma) {
        this.armas.removeIf(ap -> ap.getArma().getIdArma().equals(arma.getIdArma()));
    }
    public void addBolsaItem(Itens item, @Min(0) int qtd, @Min(0) int peso, @Min(0) int preco, String descricao) {
        BolsaPlayer bp = new BolsaPlayer();
        bp.setPlayer(this);
        bp.setItem(item);
        bp.setQtd(qtd);
        bp.setPeso(peso);
        bp.setPreco(preco);
        bp.setDescricao(descricao);
        this.bolsa.add(bp);
    }
    public void addBolsaArma(Arma arma, @Min(0) int qtd, @Min(0) int peso, @Min(0) int preco, String descricao) {
        BolsaPlayer bp = new BolsaPlayer();
        bp.setPlayer(this);
        bp.setArma(arma);
        bp.setQtd(qtd);
        bp.setPeso(peso);
        bp.setPreco(preco);
        bp.setDescricao(descricao);
        this.bolsa.add(bp);
    }
}
