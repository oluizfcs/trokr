package com.trokr.listener;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.trokr.event.TrocaFinalizadaEvent;

@Component 
public class RegistrarHistoricoListener {
    
    @EventListener 
    public void aoFinalizarTroca(TrocaFinalizadaEvent evento) {
        // TODO: registrar histórico
        System.out.println("Troca registrada no histórico");
    }
}
