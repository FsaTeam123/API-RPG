package com.rpg.core.service;

import com.rpg.adapter.in.dto.UsuarioCreateDTO;
import com.rpg.infrastructure.EmailApiClient;
import com.rpg.adapter.out.CodigoVerificacaoRepository;
import com.rpg.core.model.*;
import com.rpg.adapter.out.UsuarioRepository;
import com.rpg.adapter.out.SexoRepository;
import com.rpg.adapter.out.PerfilRepository;
import com.rpg.infrastructure.EmailTemplates;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.security.SecureRandom;
import java.time.Duration;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;
    private final CodigoVerificacaoRepository codigoRepository;
    private final PasswordEncoder passwordEncoder;
    private final SexoRepository sexoRepository;
    private final PerfilRepository perfilRepository;
    private final EmailApiClient emailApiClient;
    // TTL padrão (ajuste conforme sua regra de negócio)
    private static final Duration CODE_TTL = Duration.ofMinutes(15);

    private static final char[] ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
    private static final SecureRandom RNG = new SecureRandom();


    public UsuarioService(UsuarioRepository repository, PasswordEncoder passwordEncoder,
                          SexoRepository sexoRepository, PerfilRepository perfilRepository,
                          CodigoVerificacaoRepository codigoRepository, EmailApiClient emailApiClient) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.sexoRepository = sexoRepository;
        this.perfilRepository = perfilRepository;
        this.codigoRepository = codigoRepository;
        this.emailApiClient = emailApiClient;
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

    @Transactional
    public Optional<Usuario> buscarPorEmailComReset(String email) {
        gerarCodigoParaEmail(email);
        return repository.findByEmail(email);
    }

    @Transactional
    public String gerarCodigoParaEmail(String email) {
        // (Opcional) invalida ativos anteriores do mesmo e-mail
        codigoRepository.invalidarAtivosPorEmail(email);

        String codigo = gerarCodigoAleatorio(8);

        // Tentativa simples de evitar colisão global (praticamente improvável, mas seguro)
        int tentativas = 0;
        while (codigoRepository.existsByCodigo(codigo) && tentativas < 5) {
            codigo = gerarCodigoAleatorio(8);
            tentativas++;
        }
        if (codigoRepository.existsByCodigo(codigo)) {
            throw new IllegalStateException("Não foi possível gerar um código único.");
        }

        Instant agora = Instant.now();
        CodigoVerificacao entidade = CodigoVerificacao.builder()
                .email(email)
                .codigo(codigo)
                .usado(false)
                .criadoEm(agora)
                .expiraEm(agora.plus(CODE_TTL))
                .build();

        codigoRepository.save(entidade);

        String subject = "Seu código de verificação";
        String html = EmailTemplates.codigoVerificacaoHtml(codigo, (int) CODE_TTL.toMinutes());
        emailApiClient.sendEmail(email, subject, html);

        return codigo;
    }

    private String gerarCodigoAleatorio(int len) {
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(ALPHABET[RNG.nextInt(ALPHABET.length)]);
        }
        return sb.toString();
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
                usuario.getDtcCriacao(),
                usuario.getAtivo()
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

    @Transactional
    public boolean codigoValido(String email, String codigo) {
        int linhas = codigoRepository.consumirCodigo(email, codigo, Instant.now());
        return linhas > 0; // se consumiu (marcou usado=true), então era válido
    }
}
