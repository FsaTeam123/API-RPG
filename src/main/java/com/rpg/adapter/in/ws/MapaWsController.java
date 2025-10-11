// src/main/java/com/rpg/adapter/in/ws/MapaWsController.java
package com.rpg.adapter.in.ws;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties; // 👈 novo
import com.rpg.core.service.MapaService;

@Controller
@RequiredArgsConstructor
public class MapaWsController {

    private final SimpMessagingTemplate messaging;
    private final MapaService mapaService;

    /** Payload da seleção/viewport */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true) // 👈 tolera campos extras de clientes antigos/novos
    public static class MapSelect {
        public Long mapaId;
        public Double scale;     // zoom da câmera (opcional)
        public Double offsetX;   // pan X (opcional)
        public Double offsetY;   // pan Y (opcional)
        public Double mapScale;  // 👈 escala do MAPA (a que você quer propagar)
        public String sender;    // 👈 opcional: id do cliente para evitar “eco” no front
    }

    // Cliente envia em /app/mapa.{jogoId}.select
    // Servidor publica em /topic/mapa.{jogoId}.selected
    @MessageMapping("/mapa.{jogoId}.select")
    public void onMapSelect(@DestinationVariable Long jogoId, MapSelect payload) {
        if (payload == null || payload.mapaId == null) return;

        // segurança opcional: só propaga se o mapa pertence ao jogo
        if (!mapaService.mapaPertenceAoJogo(payload.mapaId, jogoId)) return;

        // retransmite exatamente o que recebeu (inclui mapScale/sender etc.)
        messaging.convertAndSend("/topic/mapa." + jogoId + ".selected", payload);
    }
}
