package com.trokr.listener;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.trokr.event.TrocaFinalizadaEvent;

@Component
public class CriarAvaliacaoListener {
    
    @EventListener 
    public void aoFinalizarTroca(TrocaFinalizadaEvent evento) {
        // TODO: criar avaliações para ambos usuários
        System.out.println("Criou 2 avaliações pendentes");
    }
}
