package com.trokr.model.state;

import com.trokr.model.Proposta;
import com.trokr.model.Proposta.EstadosProposta;

public class EstadoHomologacao implements EstadoProposta {
    public EstadosProposta getStatus() {
        return EstadosProposta.HOMOLOGACAO;
    }

    public void enviarParaHomologacao(Proposta proposta) {
        throw new IllegalStateException();
    }

    public void homologarRascunho(Proposta proposta) {
        proposta.mudarEstadoPara(new EstadoAtiva());
    }

    public void reprovarRascunho(Proposta proposta) {
        proposta.mudarEstadoPara(new EstadoRascunho());
    }

    public void editarProposta(Proposta proposta) {
        proposta.mudarEstadoPara(new EstadoRascunho());
    }

    public void aceitarContraproposta(Proposta proposta) {
        throw new IllegalStateException();
    }
    
    public void finalizar(Proposta proposta) {
        throw new IllegalStateException();
    }

    public void cancelar(Proposta proposta) {
        proposta.mudarEstadoPara(new EstadoCancelada());
    }
}
