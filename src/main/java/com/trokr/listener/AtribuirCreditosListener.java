package com.trokr.listener;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.trokr.event.TrocaFinalizadaEvent;
import com.trokr.model.Usuario;
import com.trokr.repository.UsuarioRepository;
import com.trokr.service.credito.CalculadoraCreditoService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor 
public class AtribuirCreditosListener {
    
    private final CalculadoraCreditoService calculadora;
    private final UsuarioRepository usuarioRepository;

    @EventListener 
    public void aoFinalizarTroca(TrocaFinalizadaEvent evento) {
        Usuario usuarioA = evento.getUsuarioA();
        Usuario usuarioB = evento.getUsuarioB();

        usuarioA.adicionarCreditos(calculadora.calcular(evento.getItemA()));
        usuarioB.adicionarCreditos(calculadora.calcular(evento.getItemB()));

        usuarioRepository.save(usuarioA);
        usuarioRepository.save(usuarioB);
    }
}
