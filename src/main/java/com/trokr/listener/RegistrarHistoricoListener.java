package com.trokr.listener;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.trokr.event.TrocaFinalizadaEvent;
import com.trokr.model.Historico;
import com.trokr.model.Proposta;
import com.trokr.model.StatusProposta;
import com.trokr.repository.HistoricoRepository;
import com.trokr.repository.PropostaRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RegistrarHistoricoListener {
    
    private final PropostaRepository propostaRepository;
    private final HistoricoRepository historicoRepository;

    @EventListener 
    public void aoFinalizarTroca(TrocaFinalizadaEvent evento) {
        Proposta proposta = propostaRepository.getReferenceById(evento.getPropostaId());

        StatusProposta estadoAnterior = proposta.isRaiz() ? StatusProposta.NEGOCIADA : StatusProposta.CP_NEGOCIADA;

        Historico historico = new Historico();
        historico.setProposta(proposta);
        historico.setMotivo("TROCA_FINALIZADA");
        historico.setEstadoAnterior(estadoAnterior);
        historico.setEstadoNovo(proposta.getStatus());
        historico.setUsuario(evento.getUsuarioA());
        historicoRepository.save(historico);
    }
}
