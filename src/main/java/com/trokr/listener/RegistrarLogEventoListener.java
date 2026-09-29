package com.trokr.listener;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.trokr.event.TrocaFinalizadaEvent;
import com.trokr.service.LogEventoService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor 
public class RegistrarLogEventoListener {
    
    private final LogEventoService logEventoService;

    @EventListener 
    public void aoFinalizarTroca(TrocaFinalizadaEvent evento) {
        Map<String, Object> payload = new HashMap<>();
        
        payload.put("propostaId", evento.getPropostaId());
        payload.put("usuarioA", evento.getUsuarioA().getNome());
        payload.put("usuarioB", evento.getUsuarioB().getNome());
        payload.put("itemA", evento.getItemA().getCategoria());
        payload.put("itemB", evento.getItemB().getCategoria());
        payload.put("dataConclusao", evento.getDataConclusao());

        logEventoService.registrarInfo(
            "TROCA_FINALIZADA",
            this.getClass().getName(),
            payload,
            evento.getUsuarioA().getId()
        );
    }
}
