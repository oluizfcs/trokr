package com.trokr.model.state.contraproposta;

import com.trokr.model.Proposta;
import com.trokr.model.StatusProposta;

public class EstadoCPEmAnalise implements EstadoContraproposta {
    public StatusProposta getStatus() {
        return StatusProposta.CP_EM_ANALISE;
    }

    public void enviarParaAnalise(Proposta contraproposta) {
        throw new IllegalStateException();
    }

    public void aceitarContraproposta(Proposta contraproposta) {
        contraproposta.mudarEstadoPara(new EstadoCPNegociada());
    }

    public void recusarContraproposta(Proposta contraproposta) {
        contraproposta.mudarEstadoPara(new EstadoCPRecusada());
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
