package com.trokr.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.trokr.adapter.RegistroDeEventos;
import com.trokr.model.LogEvento;
import com.trokr.model.NivelEvento;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LogEventoService {
    public final RegistroDeEventos registroDeEventosMongo;

    public void registrarInfo(String tipo, String origem, Map<String, Object> payload, Long usuarioId) {
        registroDeEventosMongo.registrar(
            tipo,
            NivelEvento.INFO,
            origem,
            payload,
            LocalDateTime.now(),
            usuarioId
        );
    }
    
    public void registrarErro(String origem, String mensagem, Exception excecao) {
        // TODO: montar LogEvento com nivel ERROR, payload
        // contendo a mensagem da exceção, e salvar
    }

    public List<LogEvento> buscarPorTipo(String tipo) {
        return registroDeEventosMongo.buscarPorTipo(tipo).stream().map(e -> (LogEvento) e).toList();
    }
}
