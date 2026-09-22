package com.trokr.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.trokr.adapter.RegistroDeEventos;
import com.trokr.model.LogEvento;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LogEventoService {
    public final RegistroDeEventos registroDeEventosMongo;

    public void registrarInfo(String origem, String mensagem, Map<String, Object> payload) {
        registroDeEventosMongo.registrar(origem, mensagem, payload);
    }
    
    public void registrarErro(String origem, String mensagem, Exception excecao) {
        // TODO: montar LogEvento com nivel ERROR, payload
        // contendo a mensagem da exceção, e salvar
    }

    public List<LogEvento> buscarPorTipo(String tipo) {
        return registroDeEventosMongo.buscarPorTipo(tipo).stream().map(e -> (LogEvento) e).toList();
    }
}
