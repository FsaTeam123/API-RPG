// com/rpg/adapter/in/dto/PericiaPlayerCreateDTO.java
package com.rpg.adapter.in.dto;

import jakarta.validation.constraints.NotNull;

public record PericiaPlayerCreateDTO(
        @NotNull Long playerId,
        @NotNull Long periciaId
) {}
