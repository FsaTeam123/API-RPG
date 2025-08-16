package com.rpg.adapter.in.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor
public class VerifyCodeRequest {
    @NotBlank @Email
    private String email;

    @NotBlank
    private String codigo;
}
