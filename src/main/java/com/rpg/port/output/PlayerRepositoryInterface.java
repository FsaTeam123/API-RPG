// com/rpg/port/output/PlayerRepositoryInterface.java
package com.rpg.port.output;

import com.rpg.core.model.Player;

import java.util.List;
import java.util.Optional;

public interface PlayerRepositoryInterface {
    List<Player> listarTodos();
    Optional<Player> buscarPorId(Long id);
    Player getPorId(Long id);
    Player salvar(Player player);
    void deletar(Long id);

    List<Player> listarPorJogo(Long idJogo);

    // opcionais (para a versão otimizada)
    default int updateFoto(Long id, byte[] foto, String mime, String nome, Long tam) { return 0; }
    default int clearFoto(Long id) { return 0; }
}
