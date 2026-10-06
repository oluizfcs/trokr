package com.trokr.listener;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.trokr.event.TrocaFinalizadaEvent;
import com.trokr.repository.UsuarioRepository;
import com.trokr.service.credito.CalculadoraCreditoService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor 
public class AtribuirCreditosListener {
    
    private final CalculadoraCreditoService calculadora;
    private final UsuarioRepository usuarioRepository;

    @Transactional
    @EventListener 
    public void aoFinalizarTroca(TrocaFinalizadaEvent evento) {
        Long idUsuarioA = evento.getUsuarioA().getId();
        Long idUsuarioB = evento.getUsuarioB().getId();

        int creditosA = calculadora.calcular(evento.getItemA());
        int creditosB = calculadora.calcular(evento.getItemB());

        usuarioRepository.adicionarCreditos(idUsuarioA, creditosA);
        usuarioRepository.adicionarCreditos(idUsuarioB, creditosB);
    }
}
