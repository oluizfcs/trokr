package com.trokr.listener;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.trokr.event.TrocaFinalizadaEvent;
import com.trokr.notification.NotificacaoPort;
// import com.trokr.service.LogEventoService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor 
public class NotificarUsuarioListener {

    private final NotificacaoPort notificacaoPort;
    // private final LogEventoService logEventoService;

    @EventListener
    public void enviarConfirmacao(TrocaFinalizadaEvent evento) {

        // TODO: melhorar mensagem de troca e conferir se ambos os usuarios recebem
        String mensagem = "Troca finalizada entre " + evento.getUsuarioA().getNome() + " e " + evento.getUsuarioB().getNome(); 
        
        // TODO: slide 16 aula 13
        notificacaoPort.notificar(evento.getUsuarioA().getId(), mensagem);
    }
}
