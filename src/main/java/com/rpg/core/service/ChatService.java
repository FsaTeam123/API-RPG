package com.rpg.core.service;


import com.rpg.core.model.ChatMessage;
import com.rpg.port.input.ChatUseCase;
import org.springframework.stereotype.Service;

@Service
public class ChatService implements ChatUseCase {
    @Override
    public void handleMessage(ChatMessage message) {
        System.out.println("Mensagem recebida: " + message.getText());
        // Aqui você pode armazenar a mensagem, disparar evento, etc.
    }
}

