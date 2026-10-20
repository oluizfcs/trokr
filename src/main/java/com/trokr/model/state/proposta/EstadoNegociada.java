package com.trokr.model.state.proposta;

import com.trokr.exception.TransicaoInvalidaException;
import com.trokr.model.Proposta;
import com.trokr.model.StatusProposta;

public class EstadoNegociada implements EstadoProposta {
    public StatusProposta getStatus() {
        return StatusProposta.NEGOCIADA;
    }

    public void enviarParaHomologacao(Proposta proposta) {
        throw new TransicaoInvalidaException(proposta.getStatus() + " Não pode passar para " + StatusProposta.HOMOLOGACAO);
    }

    public void aprovarRascunho(Proposta proposta) {
        throw new TransicaoInvalidaException(proposta.getStatus() + " Não pode passar para " + StatusProposta.ATIVA);
    }

    public void reprovarRascunho(Proposta proposta) {
        throw new TransicaoInvalidaException(proposta.getStatus() + " Não pode passar para " + StatusProposta.RASCUNHO);
    }

    public void editarProposta(Proposta proposta) {
        throw new TransicaoInvalidaException(proposta.getStatus() + " Não pode passar para " + StatusProposta.RASCUNHO);
    }

    public void selecionarContraproposta(Proposta proposta) {
        throw new TransicaoInvalidaException(proposta.getStatus() + " Não pode passar para " + StatusProposta.NEGOCIADA);
    }

    public void desistirDaNegociacao(Proposta proposta) {
        proposta.mudarEstadoPara(new EstadoAtiva());
    }

    public void confirmarTroca(Proposta proposta) {
        Long contrapropostaId = proposta.propostaRelacionada().getId();
        proposta.getContrapropostas().stream()
            .filter(cp -> !cp.getId().equals(contrapropostaId))
            .forEach(cp -> cp.comoContra().cancelar(cp));

        proposta.mudarEstadoPara(new EstadoFinalizada());
    }

    public void cancelar(Proposta proposta) {
        throw new TransicaoInvalidaException(proposta.getStatus() + " Não pode passar para " + StatusProposta.CANCELADA);
    }
}
