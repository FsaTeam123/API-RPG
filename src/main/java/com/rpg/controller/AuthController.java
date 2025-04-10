package com.rpg.controller;

import com.rpg.config.JwtUtil;
import com.rpg.dto.LoginRequest;
import com.rpg.dto.LoginResponse;
import com.rpg.dto.ResponseDTO;
import com.rpg.entity.Usuario;
import com.rpg.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UsuarioService service;
    private final JwtUtil jwtUtil;

    public AuthController(UsuarioService service, JwtUtil jwtUtil) {
        this.service = service;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        String senha = loginRequest.getSenha();

        return service.buscarPorEmail(email)
                .map(usuario -> {
                    if (service.senhaCorreta(senha, usuario.getSenha())) {
                        Map<String, Object> claims = new HashMap<>();
                        claims.put("email", email);
                        claims.put("role", usuario.getPerfil().getDescricao());

                        String token = jwtUtil.generateToken(claims);

                        LoginResponse response = new LoginResponse(usuario, token, "Success");
                        return ResponseEntity.ok(new ResponseDTO<>(200, "Login realizado com sucesso", response));
                    } else {
                        return ResponseEntity.status(401).body(new ResponseDTO<>(401, "Senha inválida", null));
                    }
                })
                .orElse(ResponseEntity.status(404).body(new ResponseDTO<>(404, "Email inválido", null)));
    }
}
