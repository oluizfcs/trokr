package com.trokr.model.state.contraproposta;

import com.trokr.model.Proposta;
import com.trokr.model.StatusProposta;

public class EstadoCPCancelada implements EstadoContraproposta {
    public StatusProposta getStatus() {
        return StatusProposta.CP_CANCELADA;
    }

    public void enviarParaAnalise(Proposta contraproposta) {
        throw new IllegalStateException();
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
        throw new IllegalStateException();
    }
}
