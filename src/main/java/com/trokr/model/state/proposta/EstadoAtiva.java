package com.trokr.model.state.proposta;

import com.trokr.model.Proposta;
import com.trokr.model.StatusProposta;

public class EstadoAtiva implements EstadoProposta {
    public StatusProposta getStatus() {
        return StatusProposta.ATIVA;
    }

    public void enviarParaHomologacao(Proposta proposta) {
        throw new IllegalStateException();
    }

    public void aprovarRascunho(Proposta proposta) {
        throw new IllegalStateException();
    }

    public void reprovarRascunho(Proposta proposta) {
        throw new IllegalStateException();
    }

    public void editarProposta(Proposta proposta) {
        proposta.mudarEstadoPara(new EstadoRascunho());
    }

    public void selecionarContraproposta(Proposta proposta) {
        proposta.mudarEstadoPara(new EstadoNegociada());
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
