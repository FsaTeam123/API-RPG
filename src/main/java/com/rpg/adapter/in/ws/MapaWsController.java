// src/main/java/com/rpg/adapter/in/ws/MapaWsController.java
package com.rpg.adapter.in.ws;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.rpg.core.service.MapaService;

@Controller
@RequiredArgsConstructor
public class MapaWsController {

    private final SimpMessagingTemplate messaging;
    private final MapaService mapaService;

    /** Payload simples da seleção */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class MapSelect {
        public Long mapaId;
        public Double scale;   // opcional (p/ sincronizar zoom, se quiser)
        public Double offsetX; // opcional
        public Double offsetY; // opcional
    }

    // Cliente envia em /app/mapa.{jogoId}.select
    // Servidor publica em /topic/mapa.{jogoId}.selected
    @MessageMapping("/mapa.{jogoId}.select")
    public void onMapSelect(@DestinationVariable Long jogoId, MapSelect payload) {
        if (payload == null || payload.mapaId == null) return;

        // (opcional) segurança: só propaga se o mapa pertence ao jogo
        if (!mapaService.mapaPertenceAoJogo(payload.mapaId, jogoId)) return;

        messaging.convertAndSend("/topic/mapa." + jogoId + ".selected", payload);
    }
}
