package com.trokr.model.state;

import com.trokr.model.Proposta;
import com.trokr.model.EstadosProposta;

public interface EstadoProposta {
    EstadosProposta getStatus();

    // Rascunho --> Homologacao
    void enviarParaHomologacao(Proposta proposta);

    // Homologacao --> Ativa
    void aprovarRascunho(Proposta proposta);

    // Homologacao --> Rascunho
    void reprovarRascunho(Proposta proposta);

    // [Homologacao, Ativa] --> Rascunho
    void editarProposta(Proposta proposta);

    // Ativa --> Negociada
    void selecionarContraproposta(Proposta proposta);

    // Negociada --> Ativa
    void desistirDaNegociacao(Proposta proposta);

    // Negociada --> Finalizada
    void confirmarTroca(Proposta proposta);

    // [Rascunho, Homologacao, Ativa] --> Cancelada
    void cancelar(Proposta proposta);
}