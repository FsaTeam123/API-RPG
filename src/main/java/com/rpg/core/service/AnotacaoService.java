    package com.rpg.core.service;

    import com.rpg.adapter.out.AcaoRepository;
    import com.rpg.adapter.out.AnotacaoRepository;
    import com.rpg.core.model.Acao;
    import com.rpg.core.model.Anotacao;
    import org.springframework.stereotype.Service;

    import java.util.List;
    import java.util.Optional;

    @Service
    public class AnotacaoService {

        private final AnotacaoRepository repository;

        public AnotacaoService(AnotacaoRepository repository) {
            this.repository = repository;
        }

        public List<Anotacao> listarTodos() {
            return repository.listarTodos();
        }

        public Optional<Anotacao> buscarPorId(Long id) {
            return repository.buscarPorId(id);
        }

        public Anotacao salvar(Anotacao obj) {
            return repository.salvar(obj);
        }

        public void deletar(Long id) {
            repository.deletar(id);
        }

        public List<Anotacao> listarPorJogoId(Long jogoId) {
            return repository.buscarPorJogoId(jogoId);
        }
    }
