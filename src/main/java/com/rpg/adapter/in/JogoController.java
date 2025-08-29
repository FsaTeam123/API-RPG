package com.rpg.adapter.in;

import com.rpg.adapter.in.dto.JogoCreateUpdateDTO;
import com.rpg.adapter.in.dto.JogoDTO;
import com.rpg.core.model.*;
import com.rpg.core.service.JogoService;
import com.rpg.port.input.JogoControllerInterface;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/jogos")
public class JogoController implements JogoControllerInterface {

    private final JogoService service;

    @PersistenceContext
    private EntityManager em;

    public JogoController(JogoService service) {
        this.service = service;
    }

    @Override
    @GetMapping
    public List<Jogo> listarTodos() {
        return service.listarTodos();
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Jogo> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    @GetMapping("/user/mestrado/{id}")
    public ResponseEntity<List<Jogo>> buscarPorIddeUsuarioMestre(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorIdMestre(id));
    }

    @Override
    @GetMapping("/user/jogador/{id}")
    public ResponseEntity<List<String>> buscarPorIddeUsuarioJogador(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorIdJogador(id));
    }

    // ===== POST com DTO (apenas IDs nos relacionamentos) =====
    @Override
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JogoDTO> criar(@RequestBody JogoCreateUpdateDTO dto) {
        if (dto.getMasterId() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        Jogo j = new Jogo();
        applyFromDto(j, dto, true);
        j.setDataCriacao(LocalDateTime.now());

        Jogo salvo = service.salvar(j);

        // Recarrega com grafo para evitar proxies e já devolver tudo resolvido
        Jogo carregado = loadGraph(salvo.getIdJogo());
        return ResponseEntity.status(HttpStatus.CREATED).body(toDTO(carregado));
    }

    // ===== PUT com DTO (ids nos relacionamentos) =====
    @Override
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JogoDTO> atualizar(@PathVariable Long id, @RequestBody JogoCreateUpdateDTO dto) {
        return service.buscarPorId(id)
                .map(existing -> {
                    applyFromDto(existing, dto, false);
                    Jogo salvo = service.salvar(existing);
                    Jogo carregado = loadGraph(salvo.getIdJogo());
                    return ResponseEntity.ok(toDTO(carregado));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (service.buscarPorId(id).isPresent()) {
            service.deletar(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    // ===== Helpers =====

    /**
     * Carrega o Jogo e associações necessárias via JOIN FETCH
     * para evitar proxies lazy no momento da serialização do DTO.
     */
    private Jogo loadGraph(Long id) {
        return em.createQuery("""
            SELECT j
              FROM Jogo j
              LEFT JOIN FETCH j.master m
              LEFT JOIN FETCH m.sexo
              LEFT JOIN FETCH m.perfil
              LEFT JOIN FETCH j.tipoJogo
              LEFT JOIN FETCH j.geracaoMundo
              LEFT JOIN FETCH j.estiloCampanha
              LEFT JOIN FETCH j.historia
              LEFT JOIN FETCH j.tema
             WHERE j.idJogo = :id
            """, Jogo.class)
                .setParameter("id", id)
                .getResultStream()
                .findFirst()
                .orElse(null);
    }

    /**
     * Mapeia entidade para JogoDTO.
     * Observação: JogoDTO usa UsuarioDTO para master e mantém as outras
     * associações (tipoJogo, geracaoMundo, ...) como entidades.
     */
    private JogoDTO toDTO(Jogo j) {
        if (j == null) return null;

        UsuarioDTO masterDTO = null;
        if (j.getMaster() != null) {
            var u = j.getMaster();
            masterDTO = new UsuarioDTO(
                    u.getIdUsuario(),
                    u.getNome(),
                    u.getNickname(),
                    u.getEmail(),
                    u.getSexo(),     // entidade Sexo (já carregada no loadGraph)
                    u.getPerfil(),   // entidade Perfil (já carregada)
                    u.getDtcCriacao(),
                    u.getAtivo()
            );
        }

        return new JogoDTO(
                j.getIdJogo(),
                masterDTO,
                j.getTitulo(),
                j.getQtdPessoas(),
                j.getIsEspecificClass(),
                j.getNivelInicial(),
                j.getTipoJogo(),       // entidades já fetchadas
                j.getGeracaoMundo(),
                j.getEstiloCampanha(),
                j.getHistoria(),
                j.getTema(),
                j.getSenha(),
                j.getDataCriacao(),
                j.getAtivo()
        );
    }

    // Copia dados do DTO para a entidade usando apenas IDs nos relacionamentos
    private void applyFromDto(Jogo j, JogoCreateUpdateDTO dto, boolean isCreate) {
        if (dto.getTitulo() != null)           j.setTitulo(dto.getTitulo());
        if (dto.getQtdPessoas() != null)       j.setQtdPessoas(dto.getQtdPessoas());
        if (dto.getIsEspecificClass() != null) j.setIsEspecificClass(dto.getIsEspecificClass());
        if (dto.getNivelInicial() != null)     j.setNivelInicial(dto.getNivelInicial());
        if (dto.getSenha() != null)            j.setSenha(dto.getSenha());
        if (dto.getAtivo() != null)            j.setAtivo(dto.getAtivo());

        if (dto.getMasterId() != null)          j.setMaster(em.getReference(Usuario.class, dto.getMasterId()));
        if (dto.getTipoJogoId() != null)        j.setTipoJogo(em.getReference(TipoJogo.class, dto.getTipoJogoId()));
        if (dto.getGeracaoMundoId() != null)    j.setGeracaoMundo(em.getReference(GeracaoMundo.class, dto.getGeracaoMundoId()));
        if (dto.getEstiloCampanhaId() != null)  j.setEstiloCampanha(em.getReference(EstiloCampanha.class, dto.getEstiloCampanhaId()));
        if (dto.getHistoriaId() != null)        j.setHistoria(em.getReference(Historia.class, dto.getHistoriaId()));
        if (dto.getTemaId() != null)            j.setTema(em.getReference(Tema.class, dto.getTemaId()));
    }
}
