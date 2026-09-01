package com.trokr.model.state;

import com.trokr.model.Proposta;
import com.trokr.model.EstadosProposta;

public class EstadoNegociada implements EstadoProposta {
    public EstadosProposta getStatus() {
        return EstadosProposta.NEGOCIADA;
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
        throw new IllegalStateException();
    }

    public void selecionarContraproposta(Proposta proposta) {
        throw new IllegalStateException();
    }
    
    public void desistirDaNegociacao(Proposta proposta) {
        proposta.mudarEstadoPara(new EstadoAtiva());
    }
    
    public void confirmarTroca(Proposta proposta) {
        // todo: cancelar todas as outras contrapropostas
        proposta.mudarEstadoPara(new EstadoFinalizada());
    }

    public void cancelar(Proposta proposta) {
        throw new IllegalStateException();
    }
}
