package com.rpg.core.service;

import com.rpg.adapter.in.dto.UsuarioCreateDTO;
import com.rpg.core.model.UsuarioDTO;
import com.rpg.core.model.Perfil;
import com.rpg.core.model.Sexo;
import com.rpg.core.model.Usuario;
import com.rpg.adapter.out.UsuarioRepository;
import com.rpg.adapter.out.SexoRepository;
import com.rpg.adapter.out.PerfilRepository;
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
    private final SexoRepository sexoRepository;
    private final PerfilRepository perfilRepository;

    public UsuarioService(UsuarioRepository repository, PasswordEncoder passwordEncoder,
                          SexoRepository sexoRepository, PerfilRepository perfilRepository) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.sexoRepository = sexoRepository;
        this.perfilRepository = perfilRepository;
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

        // Carregar as entidades completas usando os repositórios
        Sexo sexo = sexoRepository.findById(dto.getIdSexo())
                .orElseThrow(() -> new RuntimeException("Sexo não encontrado"));
        Perfil perfil = perfilRepository.findById(dto.getIdPerfil())
                .orElseThrow(() -> new RuntimeException("Perfil não encontrado"));

        usuario.setSexo(sexo);
        usuario.setPerfil(perfil);

        return usuario;
    }
}
