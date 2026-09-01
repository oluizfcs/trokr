package com.trokr.model.state;

import com.trokr.model.Proposta;
import com.trokr.model.Proposta.EstadosProposta;

public class EstadoAtiva implements EstadoProposta {
    public EstadosProposta getStatus() {
        return EstadosProposta.ATIVA;
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
        proposta.mudarEstadoPara(new EstadoRascunho());
    }

    public void aceitarContraproposta(Proposta proposta) {
        proposta.mudarEstadoPara(new EstadoNegociada());
    }
    
    public void finalizar(Proposta proposta) {
        throw new IllegalStateException();
    }

    public void cancelar(Proposta proposta) {
        proposta.mudarEstadoPara(new EstadoCancelada());
    }
}
