package com.rpg.infrastructure;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class EmailApiClient {

    private final WebClient webClient;

    public EmailApiClient(
            @Value("${mail.api.base-url:http://18.231.189.7:8080}") String baseUrl,
            WebClient.Builder builder
    ) {
        this.webClient = builder.baseUrl(baseUrl).build();
    }

    public void sendEmail(String to, String subject, String html) {
        var body = new EmailRequest(to, subject, html, true);
        webClient.post()
                .uri("/api/email/send")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(body)
                .retrieve()
                .toBodilessEntity()
                .block(); // chamada síncrona: garante envio (ou exceção) antes de prosseguir
    }

    // DTO usado só para a chamada externa
    public record EmailRequest(String to, String subject, String message, Boolean html) {}
}
