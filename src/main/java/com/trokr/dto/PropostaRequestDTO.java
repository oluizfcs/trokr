package com.trokr.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PropostaRequestDTO(
    @NotBlank(message = "descricao é obrigatória")
    String descricao,

    @NotNull(message = "usuarioId é obrigatório")
    Long usuarioId,

    @NotNull(message = "itemId é obrigatório")
    Long itemId,

    Long propostaId
) {
}
