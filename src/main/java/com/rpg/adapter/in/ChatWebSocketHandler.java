package com.rpg.adapter.in;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rpg.core.model.ChatMessage;
import com.rpg.port.input.ChatUseCase;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class ChatWebSocketHandler extends TextWebSocketHandler {

    private final ChatUseCase chatUseCase;
    private final List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

    public ChatWebSocketHandler(ChatUseCase chatUseCase) {
        this.chatUseCase = chatUseCase;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        sessions.add(session);
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        ChatMessage chatMessage = mapper.readValue(message.getPayload(), ChatMessage.class);
        chatUseCase.handleMessage(chatMessage);
        broadcast(chatMessage);
    }

    private void broadcast(ChatMessage message) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String payload = mapper.writeValueAsString(message);
        for (WebSocketSession sess : sessions) {
            if (sess.isOpen()) {
                sess.sendMessage(new TextMessage(payload));
            }
        }
    }
}

