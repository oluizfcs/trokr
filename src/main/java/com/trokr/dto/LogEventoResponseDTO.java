package com.trokr.dto;

import com.trokr.model.LogEvento;
import com.trokr.model.Usuario;

import java.time.LocalDateTime;

/**
 * Dados de saída de um Evento. Nunca devolvemos a entidade JPA diretamente
 * pela API — isso evita expor detalhes de persistência e dá liberdade para
 * o formato de entrada/saída evoluir sem quebrar o modelo de dados.
 */
public record LogEventoResponseDTO(
        String tipo,
        String mensagem,
        String nivel,
        String origem,
        Object payload,
        LocalDateTime timestamp,
        Usuario usuarioId
) {

    public static LogEventoResponseDTO fromDocumento(LogEvento evento) {
        return new LogEventoResponseDTO(
                evento.getTipo(),
                evento.getMensagem(),
                evento.getNivel(),
                evento.getOrigem(),
                evento.getPayload(),
                evento.getTimestamp(),
                evento.getUsuarioId()
        );
    }
}
