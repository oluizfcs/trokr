package com.trokr.model.state;

import com.trokr.model.Proposta;
import com.trokr.model.Proposta.EstadosProposta;

public class EstadoCancelada implements EstadoProposta {
    public EstadosProposta getStatus() {
        return EstadosProposta.CANCELADA;
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
        throw new IllegalStateException();
    }

    public void cancelar(Proposta proposta) {
        throw new IllegalStateException();
    }
}
