package com.rpg.adapter.in.dto;

// DrawMessage.java
import lombok.Data;

@Data
public class DrawMessage {
    private String type;      // "stroke:start" | "stroke:segment" | "stroke:end" | "shape:rect" | "shape:ellipse" | "clear" | "snapshot"
    private String sessionId; // id da partida (jogo)
    private String clientId;  // quem enviou
    private String strokeId;  // id do traço (quando aplicável)
    private String tool;      // "pencil" | "eraser" | "rect" | "ellipse"
    private String color;     // "#RRGGBB"
    private double worldWidth;
    private double x, y;      // ponto (mundo)
    private Double x2, y2;    // 2º ponto (formas)
    // snapshot opcional
    private String dataUrl;   // base64 PNG
    private Double originX, originY;
}
