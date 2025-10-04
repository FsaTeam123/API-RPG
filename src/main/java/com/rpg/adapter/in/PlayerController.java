// com/rpg/adapter/in/PlayerController.java
package com.rpg.adapter.in;

import com.rpg.adapter.in.dto.*;
import com.rpg.core.model.*;
import com.rpg.core.service.PlayerService;
import com.rpg.port.input.PlayerControllerInterface;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/players")
public class PlayerController implements PlayerControllerInterface {

    private final PlayerService service;

    public PlayerController(PlayerService service) {
        this.service = service;
    }

    // ===== CRUD =====
    @Override
    @GetMapping
    public List<Player> listarTodos() {
        return service.listarTodos();
    }

    @Override
    @GetMapping("/{id}")
    public Player buscarPorId(@PathVariable Long id) {
        Player player = service.getPorId(id);
        List<Player> players = new ArrayList<Player>();

        players.add(player);

        players =  toPlayer(players);

        return players.get(0);
    }

    @Override
    @GetMapping("/jogo/{idJogo}")
    public List<Player> listarPorJogo(@PathVariable Long idJogo) {
        List<Player> players = service.listarPorJogo(idJogo);

        return toPlayer(players);
    }

    @Override
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PlayerResponseDTO> criar(@RequestBody @Valid PlayerCreateDTO dto) {
        Player salvo = service.criarAPartirDeIds(dto);
        PlayerResponseDTO body = new PlayerResponseDTO(
                salvo.getIdPlayer(),
                salvo.getNome(),
                salvo.getForca(), salvo.getDestreza(), salvo.getSabedoria(), salvo.getConstituicao(), salvo.getInteligencia(), salvo.getCarisma(),
                salvo.getPv(), salvo.getPvMax(), salvo.getPvTemp(),
                salvo.getPm(), salvo.getPmMax(), salvo.getPmTemp(),
                salvo.getUsuario().getIdUsuario(),
                salvo.getJogo().getIdJogo(),
                salvo.getOrigem().getIdOrigem(),
                salvo.getRaca().getIdRaca(),
                salvo.getRiqueza().getIdRiqueza(),
                salvo.getDivindade().getIdDivindade(),
                salvo.getClasse().getIdClasse(),
                salvo.getTamanho().getIdTamanho()
        );

        return ResponseEntity
                .created(URI.create("/players/" + salvo.getIdPlayer()))
                .body(body);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<Player> atualizar(@PathVariable Long id, @RequestBody @Valid Player player) {
        return service.buscarPorId(id)
                .map(existing -> {
                    player.setIdPlayer(id);
                    return ResponseEntity.ok(service.salvar(player));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (service.buscarPorId(id).isPresent()) {
            service.deletar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // ===== FOTO (bytea) =====
    @Override
    @PutMapping(path = "/{id}/foto", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> uploadFoto(@PathVariable Long id,
                                           @RequestParam("file") MultipartFile file) throws IOException {
        service.salvarFoto(id, file.getOriginalFilename(), file.getContentType(), file.getBytes());
        return ResponseEntity.noContent().build();
    }

    @Override
    @GetMapping(path = "/{id}/foto")
    public ResponseEntity<byte[]> baixarFoto(@PathVariable Long id) {
        return service.buscarFoto(id)
                .map(f -> ResponseEntity.ok()
                        .contentType(f.mime() != null ? MediaType.parseMediaType(f.mime())
                                : MediaType.APPLICATION_OCTET_STREAM)
                        .header(HttpHeaders.CONTENT_DISPOSITION,
                                "inline; filename=\"" + (f.nome() != null ? f.nome() : "player-foto") + "\"")
                        .contentLength(f.tamanho() != null ? f.tamanho() : f.bytes().length)
                        .body(f.bytes()))
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    @DeleteMapping(path = "/{id}/foto")
    public ResponseEntity<Void> apagarFoto(@PathVariable Long id) {
        service.removerFoto(id);
        return ResponseEntity.noContent().build();
    }

    public List<Player> toPlayer(List<Player> players) {
        List<Player> temp = new ArrayList<>();

        for (int i = 0; i < players.size(); i++) {
            Player playerTemp = new Player();
            playerTemp.setIdPlayer(players.get(i).getIdPlayer());
            playerTemp.setNome(players.get(i).getNome());
            playerTemp.setFoto(players.get(i).getFoto());
            playerTemp.setFotoMime(players.get(i).getFotoMime());
            playerTemp.setFotoNome(players.get(i).getFotoNome());
            playerTemp.setFotoTam(players.get(i).getFotoTam());
            playerTemp.setFotoAtualizadaEm(players.get(i).getFotoAtualizadaEm());
            playerTemp.setForca(players.get(i).getForca());
            playerTemp.setDestreza(players.get(i).getDestreza());
            playerTemp.setSabedoria(players.get(i).getSabedoria());
            playerTemp.setConstituicao(players.get(i).getConstituicao());
            playerTemp.setInteligencia(players.get(i).getInteligencia());
            playerTemp.setCarisma(players.get(i).getCarisma());
            playerTemp.setPv(players.get(i).getPv());
            playerTemp.setPvMax(players.get(i).getPvMax());
            playerTemp.setPvTemp(players.get(i).getPvTemp());
            playerTemp.setPm(players.get(i).getPm());
            playerTemp.setPmMax(players.get(i).getPmMax());
            playerTemp.setPmTemp(players.get(i).getPmTemp());

            playerTemp.setUsuario(
                    new Usuario(
                            players.get(i).getUsuario().getIdUsuario(),
                            players.get(i).getUsuario().getNome(),
                            players.get(i).getUsuario().getNickname(),
                            players.get(i).getUsuario().getSexo(),
                            null,
                            players.get(i).getUsuario().getEmail(),
                            players.get(i).getUsuario().getPerfil(),
                            players.get(i).getUsuario().getDtcCriacao(),
                            players.get(i).getUsuario().getAtivo(),
                            players.get(i).getUsuario().getOnline()
                    )
            );

            playerTemp.setJogo(
                    new Jogo(
                            players.get(i).getJogo().getIdJogo(),
                            players.get(i).getJogo().getMaster(),
                            players.get(i).getJogo().getTitulo(),
                            players.get(i).getJogo().getQtdPessoas(),
                            players.get(i).getJogo().getIsEspecificClass(),
                            players.get(i).getJogo().getNivelInicial(),
                            players.get(i).getJogo().getTipoJogo(),
                            players.get(i).getJogo().getGeracaoMundo(),
                            players.get(i).getJogo().getEstiloCampanha(),
                            players.get(i).getJogo().getHistoria(),
                            players.get(i).getJogo().getTema(),
                            null,
                            players.get(i).getJogo().getDataCriacao(),
                            players.get(i).getJogo().getAtivo(),
                            players.get(i).getJogo().getPlayerAtivos()
                    )
            );

            playerTemp.setOrigem(
                    new Origem(
                            players.get(i).getOrigem().getIdOrigem(),
                            players.get(i).getOrigem().getNome(),
                            players.get(i).getOrigem().getDescricao(),
                            players.get(i).getOrigem().getAtivo(),
                            players.get(i).getOrigem().getImagem(),
                            players.get(i).getOrigem().getImagemContentType(),
                            players.get(i).getOrigem().getImagemFilename()
                    )
            );

            playerTemp.setRaca(
                    new Raca(
                            players.get(i).getRaca().getIdRaca(),
                            players.get(i).getRaca().getNome(),
                            players.get(i).getRaca().getDescricao(),
                            players.get(i).getRaca().getAtivo(),
                            players.get(i).getRaca().getFoto(),
                            players.get(i).getRaca().getFotoMime(),
                            null,
                            null,
                            null,
                            players.get(i).getRaca().getHabilidades()
                    )
            );

            playerTemp.setRiqueza(
                    new Riqueza(
                            players.get(i).getRiqueza().getIdRiqueza(),
                            players.get(i).getRiqueza().getTibarOuro(),
                            players.get(i).getRiqueza().getTibarPrata(),
                            players.get(i).getRiqueza().getTibarCobre()
                    )
            );

            playerTemp.setDivindade(
                    new Divindade(
                            players.get(i).getDivindade().getIdDivindade(),
                            players.get(i).getDivindade().getNome(),
                            players.get(i).getDivindade().getDescricao(),
                            players.get(i).getDivindade().getAtivo(),
                            players.get(i).getDivindade().getImagem(),
                            players.get(i).getDivindade().getImagemContentType(),
                            players.get(i).getDivindade().getImagemFilename()
                    )
            );

            playerTemp.setClasse(
                    new Classe(
                            players.get(i).getClasse().getIdClasse(),
                            players.get(i).getClasse().getNome(),
                            players.get(i).getClasse().getDescricao(),
                            players.get(i).getClasse().getPvInit(),
                            players.get(i).getClasse().getPvNivel(),
                            players.get(i).getClasse().getAtributoPV(),
                            players.get(i).getClasse().getPmInit(),
                            players.get(i).getClasse().getPmNivel(),
                            players.get(i).getClasse().getProeficienciasClasse(),
                            players.get(i).getClasse().getPericiasClasse(),
                            null,
                            null,
                            null,
                            players.get(i).getClasse().getAtivo()
                    )
            );

            playerTemp.setTamanho(players.get(i).getTamanho());
            playerTemp.setTamanho(
                    new Tamanho(
                            players.get(i).getTamanho().getIdTamanho(),
                            players.get(i).getTamanho().getNome(),
                            players.get(i).getTamanho().getDeslocamento(),
                            players.get(i).getTamanho().getDescricao(),
                            players.get(i).getTamanho().getModFutivi(),
                            players.get(i).getTamanho().getModManobra(),
                            players.get(i).getTamanho().getAtivo()
                    )
            );

            temp.add(playerTemp);
        }

        return temp;
    }
}
