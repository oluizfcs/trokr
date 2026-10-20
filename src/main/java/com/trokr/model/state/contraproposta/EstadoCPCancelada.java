package com.trokr.model.state.contraproposta;

import com.trokr.exception.TransicaoInvalidaException;
import com.trokr.model.Proposta;
import com.trokr.model.StatusProposta;

public class EstadoCPCancelada implements EstadoContraproposta {
    public StatusProposta getStatus() {
        return StatusProposta.CP_CANCELADA;
    }

    public void enviarParaAnalise(Proposta contraproposta) {
        throw new TransicaoInvalidaException(contraproposta.getStatus().toString() + " Não pode passar para EM_ANALISE");
    }

    public void aceitarContraproposta(Proposta contraproposta) {
        throw new TransicaoInvalidaException(contraproposta.getStatus().toString() + " Não pode passar para NEGOCIADA");
    }

    public void recusarContraproposta(Proposta contraproposta) {
        throw new TransicaoInvalidaException(contraproposta.getStatus().toString() + " Não pode passar para RECUSADA");
    }

    public void confirmarTroca(Proposta contraproposta) {
        throw new TransicaoInvalidaException(contraproposta.getStatus().toString() + " Não pode passar para FINALIZADA");
    }

    public void recusarTroca(Proposta contraproposta) {
        throw new TransicaoInvalidaException(contraproposta.getStatus().toString() + " Não pode passar para RECUSADA");
    }

    public void cancelar(Proposta contraproposta) {
        throw new TransicaoInvalidaException(contraproposta.getStatus().toString() + " Não pode passar para CANCELADA");
    }
}
