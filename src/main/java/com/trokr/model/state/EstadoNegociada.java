package com.trokr.model.state;

import com.trokr.model.Proposta;
import com.trokr.model.Proposta.EstadosProposta;

public class EstadoNegociada implements EstadoProposta {
    public EstadosProposta getStatus() {
        return EstadosProposta.NEGOCIADA;
    }

    public void enviarParaHomologacao(Proposta proposta) {
        throw new IllegalStateException();
    }

    public void homologarRascunho(Proposta proposta) {
        throw new IllegalStateException();
    }

    public void reprovarRascunho(Proposta proposta) {
        throw new IllegalStateException();
    }

    public void editarProposta(Proposta proposta) {
        throw new IllegalStateException();
    }

    public void aceitarContraproposta(Proposta proposta) {
        throw new IllegalStateException();
    }
    
    public void finalizar(Proposta proposta) {
        // todo: cancelar todas as outras contrapropostas
        proposta.mudarEstadoPara(new EstadoFinalizada());
    }

    public void cancelar(Proposta proposta) {
        throw new IllegalStateException();
    }
}
