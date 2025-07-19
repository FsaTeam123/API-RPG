package com.rpg.port.input;


import com.rpg.core.model.ChatMessage;

public interface ChatUseCase {
    void handleMessage(ChatMessage message);
}

