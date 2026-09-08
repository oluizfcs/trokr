package com.trokr.model.state.contraproposta;

import com.trokr.model.Proposta;
import com.trokr.model.StatusProposta;

public class EstadoCPRascunho implements EstadoContraproposta {
    public StatusProposta getStatus() {
        return StatusProposta.CP_RASCUNHO;
    }

    public void enviarParaAnalise(Proposta contraproposta) {
        contraproposta.mudarEstadoPara(new EstadoCPEmAnalise());
    }

    public void aceitarContraproposta(Proposta contraproposta) {
        throw new IllegalStateException();
    }

    public void recusarContraproposta(Proposta contraproposta) {
        throw new IllegalStateException();
    }

    public void confirmarTroca(Proposta contraproposta) {
        throw new IllegalStateException();
    }

    public void recusarTroca(Proposta contraproposta) {
        throw new IllegalStateException();
    }

    public void cancelar(Proposta contraproposta) {
        contraproposta.mudarEstadoPara(new EstadoCPCancelada());
    }
}
