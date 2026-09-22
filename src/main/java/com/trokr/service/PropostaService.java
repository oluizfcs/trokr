package com.trokr.service;

import java.util.List;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trokr.event.TrocaFinalizadaEvent;
import com.trokr.exception.ResourceNotFoundException;
import com.trokr.model.Proposta;
import com.trokr.model.StatusProposta;
import com.trokr.repository.PropostaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PropostaService {
    private final PropostaRepository propostaRepository;
    private final ApplicationEventPublisher eventPublisher;

    public List<Proposta> listarPropostasAtivas() {
        return propostaRepository.findByPropostaIsNull().stream()
            .filter(p -> p.getStatus() == StatusProposta.ATIVA
                || p.getStatus() == StatusProposta.NEGOCIADA)
            .toList();
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

        return propostaRepository.save(proposta);
    }

    public Proposta reprovar(Long id) {
        Proposta proposta = buscarPorId(id);
        proposta.comoRaiz().reprovarRascunho(proposta);

        return propostaRepository.save(proposta);
    }

    public Proposta selecionarContraproposta(Long id, Long contraId) {
        Proposta proposta = buscarPorId(id);
        Proposta contraproposta = buscarPorId(contraId);

        proposta.comoRaiz().selecionarContraproposta(proposta);
        contraproposta.comoContra().aceitarContraproposta(contraproposta);

        propostaRepository.save(contraproposta);
        return propostaRepository.save(proposta);
    }

    @Transactional
    public Proposta confirmarTroca(Long id) {
        Proposta proposta = buscarPorId(id);
        Proposta relacionada = proposta.propostaRelacionada();

        if(proposta.isRaiz()) {
            proposta.comoRaiz().confirmarTroca(proposta);
            relacionada.comoContra().confirmarTroca(relacionada);
        } else {
            proposta.comoContra().confirmarTroca(proposta);
            relacionada.comoRaiz().confirmarTroca(relacionada);
        }

        // TODO: delegar para o estado
        Proposta raiz = proposta.isRaiz() ? proposta : relacionada;
        raiz.getContrapropostas().stream()
                .filter(cp -> !cp.getId().equals(proposta.getId()))
                .filter(PropostaService::podeSerCancelada)
                .forEach(cp -> cp.comoContra().cancelar(cp));

        notificarTrocaFinalizada();

        propostaRepository.saveAll(raiz.getContrapropostas());
        return propostaRepository.save(proposta);
    }

    @Transactional
    public Proposta cancelar(Long id) {
        Proposta proposta = buscarPorId(id);

        if(proposta.isRaiz()) {
            proposta.comoRaiz().cancelar(proposta);
            proposta.getContrapropostas().stream()
                    .filter(PropostaService::podeSerCancelada)
                    .forEach(cp -> cp.comoContra().cancelar(cp));

            propostaRepository.saveAll(proposta.getContrapropostas());
            return propostaRepository.save(proposta);
        }

        proposta.comoContra().cancelar(proposta);

        return propostaRepository.save(proposta);
    }

    public Proposta desistir(Long id) {
        Proposta proposta = buscarPorId(id);
        proposta.comoRaiz().desistirDaNegociacao(proposta);

        return propostaRepository.save(proposta);
    }

    public Proposta recusar(Long id) {
        Proposta contraproposta = buscarPorId(id);

        switch (contraproposta.getStatus()) {
            case CP_EM_ANALISE -> contraproposta.comoContra().recusarContraproposta(contraproposta);
            case CP_NEGOCIADA  -> contraproposta.comoContra().recusarTroca(contraproposta);
            default -> throw new IllegalStateException();
        }

        return propostaRepository.save(contraproposta);
    }

    private static boolean podeSerCancelada(Proposta contraproposta) {
        return contraproposta.getStatus() == StatusProposta.CP_RASCUNHO
                || contraproposta.getStatus() == StatusProposta.CP_EM_ANALISE;
    }

    // TODO: terminar método
    private void notificarTrocaFinalizada() {
        // eventPublisher.publishEvent(new TrocaFinalizadaEvent(
            
        // ));
    }
}
