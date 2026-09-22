package com.trokr.model.state.contraproposta;

import com.trokr.model.Proposta;
import com.trokr.model.StatusProposta;

public class EstadoCPNegociada implements EstadoContraproposta {
    public StatusProposta getStatus() {
        return StatusProposta.CP_NEGOCIADA;
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
        contraproposta.mudarEstadoPara(new EstadoCPFinalizada());
    }

    public void recusarTroca(Proposta contraproposta) {
        contraproposta.mudarEstadoPara(new EstadoCPRecusada());
    }

    public void cancelar(Proposta contraproposta) {
        throw new IllegalStateException();
    }
}
