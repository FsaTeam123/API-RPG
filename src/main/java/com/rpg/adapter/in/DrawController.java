package com.rpg.adapter.in;

// DrawController.java
import com.rpg.adapter.in.dto.DrawMessage;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class DrawController {
    private final SimpMessagingTemplate broker;
    public DrawController(SimpMessagingTemplate broker){ this.broker = broker; }

    // Cliente envia para /app/sessao.{id}.draw  -> servidor publica em /topic/sessao.{id}
    @MessageMapping("/sessao.{sid}.draw")
    public void relay(@DestinationVariable String sid, @Payload DrawMessage msg) {
        broker.convertAndSend("/topic/sessao." + sid, msg);
    }

    // (opcional) pedido de snapshot
    @MessageMapping("/sessao.{sid}.snapshot.request")
    public void snapshotRequest(@DestinationVariable String sid, @Payload DrawMessage msg) {
        // aqui você pode buscar de um storage e publicar, ou deixar que um "host" responda
        // broker.convertAndSend("/topic/sessao."+sid, snapshotMsg);
    }
}
