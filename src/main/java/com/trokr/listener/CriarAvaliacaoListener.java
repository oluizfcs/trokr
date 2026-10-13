package com.trokr.listener;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.trokr.event.TrocaFinalizadaEvent;
import com.trokr.model.Avaliacao;
import com.trokr.model.StatusAvaliacao;
import com.trokr.repository.AvaliacaoRepository;
import com.trokr.repository.PropostaRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CriarAvaliacaoListener {
    
    private final PropostaRepository propostaRepository;
    private final AvaliacaoRepository avaliacaoRepository;

    @EventListener 
    public void aoFinalizarTroca(TrocaFinalizadaEvent evento) {
        Avaliacao av1 = new Avaliacao();
        Avaliacao av2 = new Avaliacao();
        av1.setStatus(StatusAvaliacao.PENDENTE);
        av2.setStatus(StatusAvaliacao.PENDENTE);
        av1.setProposta(propostaRepository.getReferenceById(evento.getPropostaId()));
        av2.setProposta(propostaRepository.getReferenceById(evento.getPropostaId()));

        av1.setUsuario(evento.getUsuarioA());
        av1.setUsuarioAvaliado(evento.getUsuarioB());
        av2.setUsuario(evento.getUsuarioB());
        av2.setUsuarioAvaliado(evento.getUsuarioA());

        avaliacaoRepository.save(av1);
        avaliacaoRepository.save(av2);
    }
}
