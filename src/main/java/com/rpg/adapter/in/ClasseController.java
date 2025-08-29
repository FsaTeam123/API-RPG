package com.rpg.adapter.in;

import com.rpg.adapter.in.dto.*;
import com.rpg.core.model.*;
import com.rpg.core.service.ClasseService;
import com.rpg.port.input.ClasseControllerInterface;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/classes")
public class ClasseController implements ClasseControllerInterface {

    private final ClasseService service;

    public ClasseController(ClasseService service) {
        this.service = service;
    }

    // ---------- GET ALL (com imagem em Base64 no payload) ----------
    @Override
    @GetMapping
    public List<ClasseDTO> listarTodos() {
        return service.listarTodos().stream()
                .map(this::toDTOWithImage)
                .collect(Collectors.toList());
    }

    // ---------- GET BY ID (com imagem em Base64 no payload) ----------
    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ClasseDTO> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(c -> ResponseEntity.ok(toDTOWithImage(c)))
                .orElse(ResponseEntity.notFound().build());
    }

    // ---------- POST - multipart/form-data ----------
    // Envie partes:
    //  - "dados": JSON de ClasseCreateUpdateDTO
    //  - "imagem": arquivo (opcional)
    @Override
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ClasseDTO> criarMultipart(
            @RequestPart("dados") ClasseCreateUpdateDTO dto,
            @RequestPart(value = "imagem", required = false) MultipartFile imagem) {

        Classe entity = fromCreateUpdateDTO(dto);

        if (imagem != null && !imagem.isEmpty()) {
            try {
                entity.setImagem(imagem.getBytes());
                entity.setImagemContentType(imagem.getContentType());
                entity.setImagemFilename(imagem.getOriginalFilename());
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
        }

        Classe salvo = service.salvar(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(toDTOWithImage(salvo));
    }

    // ---------- POST - application/json (com Base64) ----------
    @Override
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClasseDTO> criarJson(@RequestBody ClasseCreateUpdateDTO dto) {
        Classe entity = fromCreateUpdateDTO(dto);

        if (dto.getImagemBase64() != null && !dto.getImagemBase64().isBlank()) {
            entity.setImagem(Base64.getDecoder().decode(dto.getImagemBase64()));
            entity.setImagemContentType(dto.getImagemContentType());
            entity.setImagemFilename(dto.getImagemFilename());
        }

        Classe salvo = service.salvar(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(toDTOWithImage(salvo));
    }

    // ---------- PUT (multipart) ----------
    @Override
    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ClasseDTO> atualizarMultipart(
            @PathVariable Long id,
            @RequestPart("dados") ClasseCreateUpdateDTO dto,
            @RequestPart(value = "imagem", required = false) MultipartFile imagem) {

        Optional<Classe> opt = service.buscarPorId(id);
        if (opt.isEmpty()) return ResponseEntity.notFound().build();

        Classe entity = opt.get();
        applyUpdate(entity, dto);

        if (imagem != null && !imagem.isEmpty()) {
            try {
                entity.setImagem(imagem.getBytes());
                entity.setImagemContentType(imagem.getContentType());
                entity.setImagemFilename(imagem.getOriginalFilename());
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
        }
        Classe salvo = service.salvar(entity);
        return ResponseEntity.ok(toDTOWithImage(salvo));
    }

    // ---------- PUT (JSON com Base64) ----------
    @Override
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClasseDTO> atualizarJson(
            @PathVariable Long id,
            @RequestBody ClasseCreateUpdateDTO dto) {

        Optional<Classe> opt = service.buscarPorId(id);
        if (opt.isEmpty()) return ResponseEntity.notFound().build();

        Classe entity = opt.get();
        applyUpdate(entity, dto);

        if (dto.getImagemBase64() != null && !dto.getImagemBase64().isBlank()) {
            entity.setImagem(Base64.getDecoder().decode(dto.getImagemBase64()));
            entity.setImagemContentType(dto.getImagemContentType());
            entity.setImagemFilename(dto.getImagemFilename());
        }

        Classe salvo = service.salvar(entity);
        return ResponseEntity.ok(toDTOWithImage(salvo));
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

    @GetMapping("/{id}/imagem")
    public ResponseEntity<byte[]> downloadImagem(@PathVariable Long id) {
        return service.buscarPorId(id)
                .filter(c -> c.getImagem() != null && c.getImagem().length > 0)
                .map(c -> ResponseEntity.ok()
                        .contentType(parseMediaTypeSafe(c.getImagemContentType()))
                        .header(HttpHeaders.CONTENT_DISPOSITION,
                                "inline; filename=\"" +
                                        (c.getImagemFilename() != null ? c.getImagemFilename() : "imagem") + "\"")
                        .body(c.getImagem()))
                .orElse(ResponseEntity.notFound().build());
    }

    // ---------- helpers ----------
    private ClasseDTO toDTOWithImage(Classe c) {
        ClasseDTO dto = new ClasseDTO();
        dto.setIdClasse(c.getIdClasse());
        dto.setNome(c.getNome());
        dto.setDescricao(c.getDescricao());
        dto.setPvInit(c.getPvInit());
        dto.setPvNivel(c.getPvNivel());

        if (c.getAtributoPV() != null) {
            dto.setAtributoPV(new AtributoDTO(
                    c.getAtributoPV().getIdAtributo(),
                    c.getAtributoPV().getNome(),
                    c.getAtributoPV().getDescricao()
            ));
        }

        dto.setPmInit(c.getPmInit());
        dto.setPmNivel(c.getPmNivel());
        dto.setAtivo(c.getAtivo());

        if (c.getImagem() != null && c.getImagem().length > 0) {
            dto.setImagemBase64(Base64.getEncoder().encodeToString(c.getImagem()));
            dto.setImagemContentType(c.getImagemContentType());
            dto.setImagemFilename(c.getImagemFilename());
        }

        // Proeficiências
        dto.setProeficiencias(
                c.getProeficienciasClasse().stream()
                        .map(pc -> pc.getProeficiencia())
                        .distinct()
                        .map(p -> new ProeficienciaDTO(p.getIdProeficiencia(), p.getNome(), p.getDescricao(), p.getAtivo()))
                        .toList()
        );

        // NOVO: Perícias
        dto.setPericias(
                c.getPericiasClasse().stream()
                        .map(pc -> pc.getPericia())
                        .distinct()
                        .map(p -> new PericiaDTO(p.getIdPericia(), p.getNome(), p.getDescricao(), p.getAtivo()))
                        .toList()
        );

        return dto;
    }

    private Classe fromCreateUpdateDTO(ClasseCreateUpdateDTO dto) {
        Classe c = new Classe();
        c.setNome(dto.getNome());
        c.setDescricao(dto.getDescricao());
        c.setPvInit(dto.getPvInit());
        c.setPvNivel(dto.getPvNivel());
        if (dto.getAtributoPVId() != null) c.setAtributoPV(new Atributo(dto.getAtributoPVId()));
        c.setPmInit(dto.getPmInit());
        c.setPmNivel(dto.getPmNivel());
        c.setAtivo(dto.getAtivo());

        // vínculos de proeficiencia
        if (dto.getProeficienciaIds() != null && !dto.getProeficienciaIds().isEmpty()) {
            dto.getProeficienciaIds().forEach(pid -> {
                ProeficienciaClasse pc = new ProeficienciaClasse();
                pc.setClasse(c);
                pc.setProeficiencia(new Proeficiencia(pid));
                c.getProeficienciasClasse().add(pc);
            });
        }

        // NOVO: vínculos de perícia
        if (dto.getPericiaIds() != null && !dto.getPericiaIds().isEmpty()) {
            dto.getPericiaIds().forEach(pid -> {
                PericiaClasse pc = new PericiaClasse();
                pc.setClasse(c);
                pc.setPericia(new Pericia(pid));
                c.getPericiasClasse().add(pc);
            });
        }

        return c;
    }

    private void applyUpdate(Classe entity, ClasseCreateUpdateDTO dto) {
        entity.setNome(dto.getNome());
        entity.setDescricao(dto.getDescricao());
        entity.setPvInit(dto.getPvInit());
        entity.setPvNivel(dto.getPvNivel());
        if (dto.getAtributoPVId() != null) entity.setAtributoPV(new Atributo(dto.getAtributoPVId()));
        entity.setPmInit(dto.getPmInit());
        entity.setPmNivel(dto.getPmNivel());
        entity.setAtivo(dto.getAtivo());

        // substitui vínculos de proeficiencia, se enviados
        if (dto.getProeficienciaIds() != null) {
            entity.getProeficienciasClasse().clear();
            dto.getProeficienciaIds().forEach(pid -> {
                ProeficienciaClasse pc = new ProeficienciaClasse();
                pc.setClasse(entity);
                pc.setProeficiencia(new Proeficiencia(pid));
                entity.getProeficienciasClasse().add(pc);
            });
        }

        // NOVO: substitui vínculos de perícia, se enviados
        if (dto.getPericiaIds() != null) {
            entity.getPericiasClasse().clear();
            dto.getPericiaIds().forEach(pid -> {
                PericiaClasse pc = new PericiaClasse();
                pc.setClasse(entity);
                pc.setPericia(new Pericia(pid));
                entity.getPericiasClasse().add(pc);
            });
        }
    }

    private MediaType parseMediaTypeSafe(String ct) {
        try { return (ct != null ? MediaType.parseMediaType(ct) : MediaType.APPLICATION_OCTET_STREAM); }
        catch (Exception e) { return MediaType.APPLICATION_OCTET_STREAM; }
    }
}
