package com.trokr.model.state.proposta;

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
        throw new IllegalStateException();
    }

    public void reprovarRascunho(Proposta proposta) {
        throw new IllegalStateException();
    }

    public void editarProposta(Proposta proposta) {
        throw new IllegalStateException();
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
