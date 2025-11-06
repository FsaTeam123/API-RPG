package com.rpg.core.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.time.Instant;

@Data
public class ChatMessage {
    private Long senderId;
    private String senderNick;
    private String text;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Instant ts;   // servidor pode preencher se vier nulo
    private String scope;   // opcional: "geral", "mapas", "personagens"...
}
