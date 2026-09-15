package com.trokr.event;

import com.trokr.model.Usuario;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TrocaFinalizadaEvent {
    private final Long propostaId;
    private final Usuario usuarioFinalizou;
}
