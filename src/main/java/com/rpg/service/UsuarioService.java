package com.rpg.service;

import com.rpg.dto.UsuarioCreateDTO;
import com.rpg.dto.UsuarioDTO;
import com.rpg.entity.Perfil;
import com.rpg.entity.Sexo;
import com.rpg.entity.Usuario;
import com.rpg.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UsuarioDTO> listarTodosDTO() {
        return repository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<UsuarioDTO> buscarPorIdDTO(Long id) {
        return repository.findById(id).map(this::toDTO);
    }

    public Usuario salvar(Usuario obj) {
        if (obj.getSenha() != null && !obj.getSenha().startsWith("$2a$")) {
            // só criptografa se já não estiver criptografada
            String senhaCriptografada = passwordEncoder.encode(obj.getSenha());
            obj.setSenha(senhaCriptografada);
        }

        if (obj.getDtcCriacao() == null) {
            obj.setDtcCriacao(LocalDateTime.now());
        }

        return repository.save(obj);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }

    public Optional<Usuario> buscarPorEmail(String email) {
        return repository.findByEmail(email);
    }

    public boolean senhaCorreta(String senhaDigitada, String senhaCriptografada) {
        return passwordEncoder.matches(senhaDigitada, senhaCriptografada);
    }

    public UsuarioDTO toDTO(Usuario usuario) {
        return new UsuarioDTO(
                usuario.getIdUsuario(),
                usuario.getNome(),
                usuario.getNickname(),
                usuario.getEmail(),
                usuario.getSexo(),
                usuario.getPerfil(),
                usuario.getDtcCriacao()
        );
    }

    public Usuario fromDTO(UsuarioCreateDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setNickname(dto.getNickname());
        usuario.setSenha(dto.getSenha());
        usuario.setEmail(dto.getEmail());

        // Relacionamento
        usuario.setSexo(new Sexo(dto.getIdSexo()));
        usuario.setPerfil(new Perfil(dto.getIdPerfil()));

        return usuario;
    }
}
