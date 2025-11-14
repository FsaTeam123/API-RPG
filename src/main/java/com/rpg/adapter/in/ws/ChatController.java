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
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class ChatController {
    private final SimpMessagingTemplate messaging;

    // REST client para o agente
    private final RestClient agenteClient = RestClient.builder()
            .baseUrl("http://18.231.189.7:5001")
            .build();

    private final ObjectMapper mapper = new ObjectMapper();

    private static final Pattern DICE_CMD =
            Pattern.compile("(?i)/dado\\s+(\\d{1,3})d(\\d{1,4})\\b");

    // Cliente envia para /app/chat.{chatId}.message
    // Servidor publica em /topic/chat.{chatId}
    @MessageMapping("/chat.{chatId}.message")
    public void handleChatMessage(@DestinationVariable String chatId,
                                  ChatMessage message,
                                  Principal principal) {

        System.out.println(message.toString());
        System.out.println(chatId);
        if (message.getTs() == null) message.setTs(Instant.now());
        System.out.println(message.getTs());

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
                                .path("/consultar")
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

        // ---------------------- COMANDO /dado ----------------------
        Matcher dice = DICE_CMD.matcher(text);
        if (dice.find()) {
            int n = Integer.parseInt(dice.group(1));      // N
            int faces = Integer.parseInt(dice.group(2));  // M

            // limites de segurança para não abusar do servidor
            n = Math.min(Math.max(n, 1), 100);            // 1..100
            faces = Math.min(Math.max(faces, 1), 10000);  // 1..10000

            List<Integer> rolls = new ArrayList<>(n);
            ThreadLocalRandom rng = ThreadLocalRandom.current();

            // sua regra: 0..M (inclusive)
            for (int i = 0; i < n; i++) {
                int v = rng.nextInt(0, faces + 1); // 0..faces
                rolls.add(v);
            }

            int total = rolls.stream().mapToInt(Integer::intValue).sum();
            String expr = rolls.stream().map(String::valueOf).collect(Collectors.joining(" + "));
            String pretty = (n == 1)
                    ? String.format("%s", expr)
                    : String.format("%s = %d", expr, total);

            ChatMessage result = new ChatMessage();
            result.setSenderId(message.getSenderId());           // mostra como o próprio remetente
            result.setSenderNick(message.getSenderNick());
            result.setScope(message.getScope());
            result.setTs(Instant.now());
            result.setText(String.format("🎲 %s rolou %dd%d: %s",
                    safe(message.getSenderNick()), n, faces, pretty));

            messaging.convertAndSend("/topic/chat." + chatId, result);
        }
// -------------------- FIM /dado ----------------------------
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
