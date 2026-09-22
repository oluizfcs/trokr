package com.trokr.adapter;

import java.util.List;

import org.springframework.stereotype.Component;

import com.trokr.model.LogEvento;
import com.trokr.repository.LogEventoRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RegistroDeEventosMongo implements RegistroDeEventos {
    private final LogEventoRepository logEventoRepository;

    public void registrar(String tipo, String mensagem, Object payload) {
        LogEvento evento = new LogEvento();
        evento.setTipo(tipo);
        evento.setMensagem(mensagem);
        evento.setPayload(payload);
        logEventoRepository.insert(evento);
    }

    public List<Object> buscarPorTipo(String tipo) {
        return logEventoRepository.findAll().stream().map(e -> (Object) e).toList();
    }
}
