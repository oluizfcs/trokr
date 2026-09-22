package com.trokr.event;

import java.time.LocalDateTime;

import com.trokr.model.Item;
import com.trokr.model.Usuario;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TrocaFinalizadaEvent {
    private final Long propostaId;
    private final Usuario usuarioA, usuarioB;
    private final Item itemA, itemB;
    private final LocalDateTime dataConclusao;
}
