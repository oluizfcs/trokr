package com.trokr.model.state.contraproposta;

import com.trokr.exception.TransicaoInvalidaException;
import com.trokr.model.Proposta;
import com.trokr.model.StatusProposta;

public class EstadoCPEmAnalise implements EstadoContraproposta {
    public StatusProposta getStatus() {
        return StatusProposta.CP_EM_ANALISE;
    }

    public void enviarParaAnalise(Proposta contraproposta) {
        throw new TransicaoInvalidaException(contraproposta.getStatus().toString() + " Não pode passar para EM_ANALISE");
    }

    public void aceitarContraproposta(Proposta contraproposta) {
        contraproposta.mudarEstadoPara(new EstadoCPNegociada());
    }

    public void recusarContraproposta(Proposta contraproposta) {
        contraproposta.mudarEstadoPara(new EstadoCPRecusada());
    }

    public void confirmarTroca(Proposta contraproposta) {
        throw new TransicaoInvalidaException(contraproposta.getStatus().toString() + " Não pode passar para FINALIZADA");
    }

    public void recusarTroca(Proposta contraproposta) {
        throw new TransicaoInvalidaException(contraproposta.getStatus().toString() + " Não pode passar para RECUSADA");
    }

    public void cancelar(Proposta contraproposta) {
        contraproposta.mudarEstadoPara(new EstadoCPCancelada());
    }
}
