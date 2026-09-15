package com.trokr.service;

import java.util.List;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.trokr.event.TrocaFinalizadaEvent;
import com.trokr.exception.ResourceNotFoundException;
import com.trokr.model.Proposta;
import com.trokr.repository.PropostaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PropostaService {
    private final PropostaRepository propostaRepository;
    private final ApplicationEventPublisher eventPublisher;

    public List<Proposta> listarPropostas() {
        return propostaRepository.findByPropostaIsNull();
    }

    public List<Proposta> listarContrapropostas(Long propostaId) {
        return propostaRepository.findByPropostaId(propostaId);
    }

    public Proposta buscarPorId(Long id) {
        return propostaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Proposta não encontrada com id " + id));
    }

    public Proposta criar(Proposta proposta) {
        return propostaRepository.save(proposta);
    }

    public Proposta atualizar(Long id, Proposta dadosAtualizados) {
        Proposta propostaExistente = buscarPorId(id);
        propostaExistente.comoRaiz().editarProposta(propostaExistente);

        propostaExistente.setDescricao(dadosAtualizados.getDescricao());
        propostaExistente.setItem(dadosAtualizados.getItem());

        return propostaRepository.save(propostaExistente);
    }

    public Proposta enviarRascunho(Long id) {
        Proposta proposta = buscarPorId(id);

        if(proposta.isRaiz()) {
            proposta.comoRaiz().enviarParaHomologacao(proposta);
        } else {
            proposta.comoContra().enviarParaAnalise(proposta);
        }

        return propostaRepository.save(proposta);
    }

    public Proposta aprovar(Long id) {
        Proposta proposta = buscarPorId(id);
        proposta.comoRaiz().aprovarRascunho(proposta);
        
        // TODO: fazer certo
        eventPublisher.publishEvent(new TrocaFinalizadaEvent(id, proposta.getUsuario()));

        System.out.println("chegou até aqui");

        return propostaRepository.save(proposta);
    }
}
