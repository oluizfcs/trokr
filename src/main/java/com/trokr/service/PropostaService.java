package com.trokr.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.trokr.exception.ResourceNotFoundException;
import com.trokr.model.Proposta;
import com.trokr.repository.PropostaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PropostaService {
    private final PropostaRepository propostaRepository;

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

    public Proposta atualizar(Long id, Proposta dadosAtualizados, Long usuarioId) {
        Proposta propostaExistente = buscarPorId(id);
        propostaExistente.setDescricao(dadosAtualizados.getDescricao());
        propostaExistente.setItem(dadosAtualizados.getItem());

        return propostaRepository.save(propostaExistente);
    }
}
