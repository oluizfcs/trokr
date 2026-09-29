package com.trokr.dto;

import com.trokr.model.LogEvento;
import com.trokr.model.NivelEvento;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * Dados de saída de um Evento. Nunca devolvemos a entidade JPA diretamente
 * pela API — isso evita expor detalhes de persistência e dá liberdade para
 * o formato de entrada/saída evoluir sem quebrar o modelo de dados.
 */
public record LogEventoResponseDTO(
        String tipo,
        NivelEvento nivel,
        String origem,
        Map<String, Object> payload,
        LocalDateTime timestamp,
        Long usuarioId
) {

    public static LogEventoResponseDTO fromDocumento(LogEvento evento) {
        return new LogEventoResponseDTO(
            evento.getTipo(),
            evento.getNivel(),
            evento.getOrigem(),
            evento.getPayload(),
            evento.getTimestamp(),
            evento.getUsuarioId()
        );
    }
}
