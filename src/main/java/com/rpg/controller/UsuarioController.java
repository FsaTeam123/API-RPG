package com.rpg.controller;

import com.rpg.dto.*;
import com.rpg.entity.Perfil;
import com.rpg.entity.Sexo;
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

    @PutMapping("/atualizar/{email}")
    public ResponseEntity<?> atualizarPorEmail(@PathVariable String email, @RequestBody UsuarioUpdateDTO dto) {
        System.out.println("AOOOBAAA");
        return service.buscarPorEmail(email)
                .map(usuario -> {
                    if (dto.getNome() != null) usuario.setNome(dto.getNome());
                    if (dto.getNickname() != null) usuario.setNickname(dto.getNickname());
                    if (dto.getSenha() != null) usuario.setSenha(dto.getSenha());
                    if (dto.getIdSexo() != null) usuario.setSexo(new Sexo(dto.getIdSexo()));
                    if (dto.getIdPerfil() != null) usuario.setPerfil(new Perfil(dto.getIdPerfil()));

                    Usuario atualizado = service.salvar(usuario);
                    return ResponseEntity.ok(new ResponseDTO<>(200, "Usuário atualizado com sucesso", service.toDTO(atualizado)));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (service.buscarPorIdDTO(id).isPresent()) {
            service.deletar(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}