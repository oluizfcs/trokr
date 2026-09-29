package com.trokr.adapter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import com.trokr.model.NivelEvento;

public interface RegistroDeEventos {
    void registrar(
        String tipo,
        NivelEvento nivel,
        String origem,
        Map<String, Object> payload,
        LocalDateTime timestamp,
        Long usuarioId
    );

    List<Object> buscarPorTipo(String tipo);
}
