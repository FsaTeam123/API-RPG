// com/rpg/adapter/in/dto/PoderPlayerCreateDTO.java
package com.rpg.adapter.in.dto;

import jakarta.validation.constraints.NotNull;

public record PoderPlayerCreateDTO(
        @NotNull Long playerId,
        @NotNull Long poderId
) {}
