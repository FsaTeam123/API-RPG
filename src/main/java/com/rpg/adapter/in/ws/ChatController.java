package com.rpg.adapter.in.ws;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rpg.core.model.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestClient;

import java.security.Principal;
import java.time.Instant;

@Controller
@RequiredArgsConstructor
public class ChatController {
    private final SimpMessagingTemplate messaging;

    // REST client para o agente
    private final RestClient agenteClient = RestClient.builder()
            .baseUrl("https://t7tsd4gbsd.execute-api.sa-east-1.amazonaws.com")
            .build();

    private final ObjectMapper mapper = new ObjectMapper();

    // Cliente envia para /app/chat.{chatId}.message
    // Servidor publica em /topic/chat.{chatId}
    @MessageMapping("/chat.{chatId}.message")
    public void handleChatMessage(@DestinationVariable String chatId,
                                  ChatMessage message,
                                  Principal principal) {

        if (message.getTs() == null) message.setTs(Instant.now());

        // 1) retransmite a mensagem original
        messaging.convertAndSend("/topic/chat." + chatId, message);

        // 2) se houver @Agente na mensagem, chama o endpoint e publica a resposta
        final String text = safe(message.getText());
        if (text.toLowerCase().contains("@agente")) {
            final String p = text.replaceAll("(?i)@agente", "").trim();
            final String perguntaFinal = p.isBlank() ? text : p; // <- final
            // pergunta = texto sem o @Agente (fallback: usa o texto todo)
            String pergunta = text.replaceAll("(?i)@agente", "").trim();
            //if (pergunta.isBlank()) pergunta = text;

            try {
                // Chamada GET: /agente/consulta?pergunta=...
                String json = agenteClient.get()
                        .uri(uriBuilder -> uriBuilder
                                .path("/agente/consulta")
                                .queryParam("pergunta", perguntaFinal)
                                .build())
                        // se seu API exigir chave/headers, descomente e ajuste:
                        //.header("x-api-key", "SUA_CHAVE_AQUI")
                        .accept(MediaType.APPLICATION_JSON)
                        .retrieve()
                        .body(String.class);

                String resposta = tryExtractResposta(json);
                if (resposta == null || resposta.isBlank()) {
                    resposta = json; // fallback: manda o body bruto
                }

                ChatMessage bot = new ChatMessage();
                bot.setSenderId(-1L);
                bot.setSenderNick("Agente");
                bot.setText(resposta);
                bot.setTs(Instant.now());
                bot.setScope(message.getScope());

                messaging.convertAndSend("/topic/chat." + chatId, bot);

            } catch (Exception ex) {
                ChatMessage err = new ChatMessage();
                err.setSenderId(-1L);
                err.setSenderNick("Agente");
                err.setText("⚠️ Não consegui consultar o agente agora.");
                err.setTs(Instant.now());
                err.setScope(message.getScope());
                messaging.convertAndSend("/topic/chat." + chatId, err);
            }
        }
    }

    private String safe(String s){ return s == null ? "" : s; }

    /** Tenta ler {"resposta": "..."} ou {"data":{"resposta":"..."}} etc. */
    private String tryExtractResposta(String json) {
        try {
            JsonNode root = mapper.readTree(json);
            if (root.hasNonNull("resposta")) return root.get("resposta").asText();
            if (root.has("data") && root.get("data").hasNonNull("resposta"))
                return root.get("data").get("resposta").asText();
            // tente outros formatos comuns, se necessário
        } catch (Exception ignored) {}
        return null;
    }
}
