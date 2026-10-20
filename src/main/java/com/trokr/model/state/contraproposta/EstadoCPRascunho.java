package com.trokr.model.state.contraproposta;

import com.trokr.exception.TransicaoInvalidaException;
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
        throw new TransicaoInvalidaException(contraproposta.getStatus() + " Não pode passar para " + StatusProposta.CP_NEGOCIADA);
    }

    public void recusarContraproposta(Proposta contraproposta) {
        throw new TransicaoInvalidaException(contraproposta.getStatus() + " Não pode passar para " + StatusProposta.CP_RECUSADA);
    }

    public void confirmarTroca(Proposta contraproposta) {
        throw new TransicaoInvalidaException(contraproposta.getStatus() + " Não pode passar para " + StatusProposta.CP_FINALIZADA);
    }

    public void recusarTroca(Proposta contraproposta) {
        throw new TransicaoInvalidaException(contraproposta.getStatus() + " Não pode passar para " + StatusProposta.CP_RECUSADA);
    }

    public void cancelar(Proposta contraproposta) {
        contraproposta.mudarEstadoPara(new EstadoCPCancelada());
    }
}
