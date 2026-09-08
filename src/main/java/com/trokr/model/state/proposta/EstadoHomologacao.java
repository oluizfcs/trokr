package com.trokr.model.state.proposta;

import com.trokr.model.Proposta;
import com.trokr.model.StatusProposta;

public class EstadoHomologacao implements EstadoProposta {
    public StatusProposta getStatus() {
        return StatusProposta.HOMOLOGACAO;
    }

    public void enviarParaHomologacao(Proposta proposta) {
        throw new IllegalStateException();
    }

    public void aprovarRascunho(Proposta proposta) {
        proposta.mudarEstadoPara(new EstadoAtiva());
    }

    public void reprovarRascunho(Proposta proposta) {
        proposta.mudarEstadoPara(new EstadoRascunho());
    }

    public void editarProposta(Proposta proposta) {
        proposta.mudarEstadoPara(new EstadoRascunho());
    }

    public void selecionarContraproposta(Proposta proposta) {
        throw new IllegalStateException();
    }
    
    public void desistirDaNegociacao(Proposta proposta) {
        throw new IllegalStateException();
    }
    
    public void confirmarTroca(Proposta proposta) {
        throw new IllegalStateException();
    }

    public void cancelar(Proposta proposta) {
        proposta.mudarEstadoPara(new EstadoCancelada());
    }
}
