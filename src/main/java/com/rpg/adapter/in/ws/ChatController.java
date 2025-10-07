package com.rpg.adapter.in.ws;

import com.rpg.core.model.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.time.Instant;

@Controller
@RequiredArgsConstructor
public class ChatController {
    private final SimpMessagingTemplate messaging;

    // Cliente envia para /app/chat.{chatId}.message
    // Servidor publica em /topic/chat.{chatId}
    @MessageMapping("/chat.{chatId}.message")
    public void handleChatMessage(@DestinationVariable String chatId,
                                  ChatMessage message,
                                  Principal principal) {
        // (opcional) garantir que o usuário pertence à sessão chatId
        // if (!membershipService.userBelongsToChat(principal.getName(), chatId)) return;

        if (message.getTs() == null) message.setTs(Instant.now());
        messaging.convertAndSend("/topic/chat." + chatId, message);
    }
}
