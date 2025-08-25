package com.rpg.adapter.in;

import com.rpg.adapter.in.dto.UsuarioCreateDTO;
import com.rpg.adapter.out.dto.ResponseDTO;
import com.rpg.adapter.out.dto.UsuarioUpdateDTO;
import com.rpg.core.model.*;
import com.rpg.core.service.UsuarioService;
import com.rpg.port.input.UsuarioControllerInterface;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import jakarta.validation.Valid;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController implements UsuarioControllerInterface {

    private final UsuarioService service;

    @Value("${app.external-base-url}")
    private String externalBaseUrl;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @Override
    @GetMapping
    public List<UsuarioDTO> listarTodos() {
        return service.listarTodosDTO();
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> buscarPorId(@PathVariable Long id) {
        return service.buscarPorIdDTO(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    @GetMapping("/email/{email:.+}")
    public ResponseEntity<UsuarioDTO> buscarPorEmail(@PathVariable String email) {
        return service.buscarPorEmail(email).map(this::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    @GetMapping("/reset/{email:.+}")
    public ResponseEntity<UsuarioDTO> buscarPorEmailComReset(@PathVariable String email) {
        return service.buscarPorEmailComReset(email).map(this::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    @PostMapping
    public ResponseEntity<ResponseDTO<UsuarioDTO>> criar(@RequestBody @Valid UsuarioCreateDTO dto) {
        Usuario usuario = service.fromDTO(dto);
        Usuario salvo = service.salvar(usuario);
        return ResponseEntity.ok(new ResponseDTO<>(201, "Usuário criado com sucesso", service.toDTO(salvo)));
    }

    @Override
    @PutMapping("/atualizar/{email}")
    public ResponseEntity<?> atualizarPorEmail(@PathVariable String email, @RequestBody UsuarioUpdateDTO dto) {
        return service.buscarPorEmail(email)
                .map(usuario -> {
                    if (dto.getNome() != null) usuario.setNome(dto.getNome());
                    if (dto.getNickname() != null) usuario.setNickname(dto.getNickname());
                    if (dto.getSenha() != null) usuario.setSenha(dto.getSenha());
                    if (dto.getIdSexo() != null) usuario.setSexo(new Sexo(dto.getIdSexo()));
                    if (dto.getIdPerfil() != null) usuario.setPerfil(new Perfil(dto.getIdPerfil()));
                    if (dto.getAtivo() != null) usuario.setAtivo(dto.getAtivo());

                    Usuario atualizado = service.salvar(usuario);
                    return ResponseEntity.ok(new ResponseDTO<>(200, "Usuário atualizado com sucesso", service.toDTO(atualizado)));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (service.buscarPorIdDTO(id).isPresent()) {
            service.deletar(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
    public UsuarioDTO toDTO(Usuario usuario) {
        return new UsuarioDTO(
                usuario.getIdUsuario(),
                usuario.getNome(),
                usuario.getNickname(),
                usuario.getEmail(),
                usuario.getSexo(),
                usuario.getPerfil(),
                usuario.getDtcCriacao(),
                usuario.getAtivo()
        );
    }

    // >>>>>>>>>> GET da foto (pega do banco) <<<<<<<<<<
    @GetMapping("/{id}/foto")
    public ResponseEntity<Resource> baixarFoto(@PathVariable Long id) {
        Optional<FotoUsuario> opt = service.obterFotoUsuario(id);
        if (opt.isEmpty() || opt.get().getDados() == null) return ResponseEntity.notFound().build();

        FotoUsuario foto = opt.get();
        MediaType mediaType = MediaType.APPLICATION_OCTET_STREAM;
        try {
            if (foto.getContentType() != null) {
                mediaType = MediaType.parseMediaType(foto.getContentType());
            }
        } catch (Exception ignored) {}

        return ResponseEntity.ok()
                .contentType(mediaType)
                .contentLength(foto.getTamanhoBytes() == null ? foto.getDados().length : foto.getTamanhoBytes())
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        ContentDisposition.inline()
                                .filename(foto.getNomeArquivo() == null ? ("usuario_" + id) : foto.getNomeArquivo())
                                .build().toString())
                .body(new ByteArrayResource(foto.getDados()));
    }

    // >>>>>>>>>> POST da foto (salva no banco) <<<<<<<<<<
    @PostMapping(path = "/{id}/foto", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ResponseDTO<String>> uploadFoto(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file) throws IOException {

        Optional<Usuario> opt = service.buscarEntidadePorId(id);
        if (opt.isEmpty()) {
            return ResponseEntity.status(404)
                    .body(new ResponseDTO<>(404, "Usuário não encontrado", null));
        }
        if (file.isEmpty()) {
            return ResponseEntity.badRequest()
                    .body(new ResponseDTO<>(400, "Arquivo vazio", null));
        }

        byte[] dados = file.getBytes();
        String contentType = Objects.requireNonNullElse(file.getContentType(), MediaType.APPLICATION_OCTET_STREAM_VALUE);
        String nomeArquivo = Objects.requireNonNullElse(file.getOriginalFilename(), "foto");

        service.salvarFotoUsuario(opt.get(), dados, contentType, nomeArquivo);

        String fotoUrl = externalBaseUrl + "/usuarios/" + id + "/foto";

        return ResponseEntity.ok(new ResponseDTO<>(200, "Foto salva com sucesso", fotoUrl));
    }
}