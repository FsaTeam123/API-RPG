package com.rpg.adapter.in.ws;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class MesaWsController {

    private final SimpMessagingTemplate messaging;

    // front envia para /app/mesa.join
    @MessageMapping("/mesa.join")
    public void joinMesa(JoinDto dto) {
        // TODO: persistir/validar associação player↔mesa
        messaging.convertAndSend("/topic/mesa." + dto.getJogoId(), Map.of(
                "type","JOIN",
                "idPlayer", dto.getIdPlayer(),
                "nome", dto.getPlayerNome(),
                "usuario", Map.of(
                        "idUsuario", dto.getUserId(),
                        "nickname", dto.getNickname(),
                        "online", 1
                )
        ));
        // opcional: status
        messaging.convertAndSend("/topic/mesa." + dto.getJogoId() + ".status", Map.of(
                "type","ONLINE", "userId", dto.getUserId()
        ));
    }

    // front envia para /app/mesa.leave
    @MessageMapping("/mesa.leave")
    public void leaveMesa(LeaveDto dto) {
        messaging.convertAndSend("/topic/mesa." + dto.getJogoId(), Map.of(
                "type","LEAVE",
                "userId", dto.getUserId(),
                "idPlayer", dto.getIdPlayer()
        ));
        messaging.convertAndSend("/topic/mesa." + dto.getJogoId() + ".status", Map.of(
                "type","OFFLINE", "userId", dto.getUserId()
        ));
    }

    // front envia para /app/status.set
    @MessageMapping("/status.set")
    public void setStatus(StatusDto dto) {
        String type = (dto.isOnline() ? "ONLINE" : "OFFLINE");
        messaging.convertAndSend("/topic/mesa." + dto.getJogoId() + ".status",
                Map.of("type", type, "userId", dto.getUserId()));
    }

    // se sua API atualizar a foto pelo REST, você pode chamar esse helper
    public void notifyPhotoUpdate(long jogoId, long userId){
        messaging.convertAndSend("/topic/mesa." + jogoId, Map.of(
                "type", "PHOTO_UPDATE", "userId", userId
        ));
    }

    @Data public static class JoinDto   { long jogoId; long idPlayer; long userId; String nickname; String playerNome; }
    @Data public static class LeaveDto  { long jogoId; long idPlayer; long userId; }
    @Data public static class StatusDto { long jogoId; long userId; boolean online; }
}
