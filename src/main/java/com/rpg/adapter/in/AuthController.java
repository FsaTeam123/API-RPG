package com.rpg.adapter.in;

import com.rpg.adapter.in.dto.VerifyCodeRequest;
import com.rpg.infrastructure.JwtUtil;
import com.rpg.adapter.in.dto.LoginRequest;
import com.rpg.adapter.out.dto.LoginResponse;
import com.rpg.adapter.out.dto.ResponseDTO;
import com.rpg.core.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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

                        // monta a fotoUrl se existir foto salva no banco
                        String fotoUrl = null;
                        if (service.obterFotoUsuario(usuario.getIdUsuario())
                                .map(f -> f.getDados() != null).orElse(false)) {
                            fotoUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                                    .path("/usuarios/")
                                    .path(String.valueOf(usuario.getIdUsuario()))
                                    .path("/foto")
                                    .toUriString();
                        }

                        if (usuario.getAtivo().equals(0)) {
                            return ResponseEntity.status(401).body(new ResponseDTO<>(401, "Usuário Inativo", null));
                        }

                        LoginResponse response = new LoginResponse(usuario, token, "Success", fotoUrl);
                        return ResponseEntity.ok(new ResponseDTO<>(200, "Login realizado com sucesso", response));
                    } else {
                        return ResponseEntity.status(401).body(new ResponseDTO<>(401, "Senha inválida", null));
                    }
                })
                .orElse(ResponseEntity.status(404).body(new ResponseDTO<>(404, "Email inválido", null)));
    }

    @PostMapping("/verify-code")
    public ResponseEntity<?> verifyCode(@Valid @RequestBody VerifyCodeRequest request) {
        boolean valido = service.codigoValido(request.getEmail(), request.getCodigo());
        Map<String, Object> claims = new HashMap<>();
        claims.put("email", request.getEmail());
        claims.put("acessoReset", request.getCodigo());
        claims.put("role", "Padrão");

        String token = jwtUtil.generateToken(claims);

        Map<String, String> responseData = new HashMap<>();
        responseData.put("token", token);

        if (valido) {
            return ResponseEntity.ok(new ResponseDTO<>(200, "Código validado com sucesso", responseData));
        }
        return ResponseEntity
                .badRequest()
                .body(new ResponseDTO<>(400, "Código inválido ou expirado", null));
    }
}
