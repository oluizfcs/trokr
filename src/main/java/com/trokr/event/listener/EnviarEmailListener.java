package com.trokr.event.listener;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.trokr.event.TrocaFinalizadaEvent;

@Component 
public class EnviarEmailListener {
    
    @EventListener 
    public void aoFinalizarTroca(TrocaFinalizadaEvent evento) {
        System.out.println("O usuário " + evento.getUsuarioFinalizou().getNome() + " finalizou a troca de id: " + evento.getPropostaId());
    }
}
