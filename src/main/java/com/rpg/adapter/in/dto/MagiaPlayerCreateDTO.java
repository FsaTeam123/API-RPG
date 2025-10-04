// com/rpg/adapter/in/dto/MagiaPlayerCreateDTO.java
package com.rpg.adapter.in.dto;

import jakarta.validation.constraints.NotNull;

public record MagiaPlayerCreateDTO(
        @NotNull Long playerId,
        @NotNull Long magiaId
) {}
