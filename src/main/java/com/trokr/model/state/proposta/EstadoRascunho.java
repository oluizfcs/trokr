package com.trokr.model.state.proposta;

import com.trokr.exception.TransicaoInvalidaException;
import com.trokr.model.Proposta;
import com.trokr.model.StatusProposta;

public class EstadoRascunho implements EstadoProposta {
    public StatusProposta getStatus() {
        return StatusProposta.RASCUNHO;
    }

    public void enviarParaHomologacao(Proposta proposta) {
        proposta.mudarEstadoPara(new EstadoHomologacao());
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
        proposta.mudarEstadoPara(new EstadoCancelada());
    }
}
