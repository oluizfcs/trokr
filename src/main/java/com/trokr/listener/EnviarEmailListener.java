package com.trokr.listener;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.trokr.event.TrocaFinalizadaEvent;
import com.trokr.notification.NotificacaoPort;
// import com.trokr.service.LogEventoService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor 
public class EnviarEmailListener {

    private final NotificacaoPort notificacaoPort;
    // private final LogEventoService logEventoService;

    @EventListener
    public void enviarConfirmacao(TrocaFinalizadaEvent evento) {
        notificacaoPort.notificar(evento.getUsuarioA().getId(), "---------------------------troca finalizada-------------------------");
    }
}
