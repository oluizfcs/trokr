package com.trokr.adapter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.trokr.model.LogEvento;
import com.trokr.model.NivelEvento;
import com.trokr.repository.LogEventoRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RegistroDeEventosMongo implements RegistroDeEventos {
    private final LogEventoRepository logEventoRepository;

    public void registrar(
        String tipo,
        NivelEvento nivel,
        String origem,
        Map<String, Object> payload,
        LocalDateTime timestamp,
        Long usuarioId
    ) {
        logEventoRepository.insert(new LogEvento(tipo, nivel, origem, payload, timestamp, usuarioId));
    }

    public List<Object> buscarPorTipo(String tipo) {
        return logEventoRepository.findAll().stream().map(e -> (Object) e).toList();
    }
}
