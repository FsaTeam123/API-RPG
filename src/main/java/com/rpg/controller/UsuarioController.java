package com.rpg.controller;

import com.rpg.dto.*;
import com.rpg.entity.Usuario;
import com.rpg.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @GetMapping
    public List<UsuarioDTO> listarTodos() {
        return service.listarTodosDTO();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> buscarPorId(@PathVariable Long id) {
        return service.buscarPorIdDTO(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ResponseDTO<UsuarioDTO>> criar(@RequestBody @Valid UsuarioCreateDTO dto) {
        Usuario usuario = service.fromDTO(dto);
        Usuario salvo = service.salvar(usuario);
        return ResponseEntity.ok(new ResponseDTO<>(201, "Usuário criado com sucesso", service.toDTO(salvo)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizar(@PathVariable Long id, @RequestBody Usuario obj) {
        return service.buscarPorIdDTO(id)
                .map(existing -> {
                    obj.setIdUsuario(id);
                    return ResponseEntity.ok(service.salvar(obj));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (service.buscarPorIdDTO(id).isPresent()) {
            service.deletar(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}