package com.trokr.model.state.proposta;

import com.trokr.exception.TransicaoInvalidaException;
import com.trokr.model.Proposta;
import com.trokr.model.StatusProposta;

public class EstadoCancelada implements EstadoProposta {
    public StatusProposta getStatus() {
        return StatusProposta.CANCELADA;
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
        throw new TransicaoInvalidaException(proposta.getStatus() + " Não pode passar para " + StatusProposta.ATIVA);
    }

    public void confirmarTroca(Proposta proposta) {
        throw new TransicaoInvalidaException(proposta.getStatus() + " Não pode passar para " + StatusProposta.FINALIZADA);
    }

    public void cancelar(Proposta proposta) {
        throw new TransicaoInvalidaException(proposta.getStatus() + " Não pode passar para " + StatusProposta.CANCELADA);
    }
}
