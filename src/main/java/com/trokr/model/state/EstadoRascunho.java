package com.trokr.model.state;

import com.trokr.model.Proposta;
import com.trokr.model.EstadosProposta;

public class EstadoRascunho implements EstadoProposta {
    public EstadosProposta getStatus() {
        return EstadosProposta.RASCUNHO;
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
