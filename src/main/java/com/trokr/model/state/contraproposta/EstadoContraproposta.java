package com.trokr.model.state.contraproposta;

import com.trokr.model.Proposta;
import com.trokr.model.StatusProposta;

public interface EstadoContraproposta {
    StatusProposta getStatus();

    // Rascunho --> Em Analise
    void enviarParaAnalise(Proposta contraproposta);

    // Em Analise --> Negociada
    void aceitarContraproposta(Proposta contraproposta);

    // Em Analise --> Recusada
    void recusarContraproposta(Proposta contraproposta);

    // Negociada --> Finalizada
    void confirmarTroca(Proposta contraproposta);

    // Negociada --> Recusada
    void recusarTroca(Proposta contraproposta);

    // [Rascunho, Em Análise] --> Cancelada
    void cancelar(Proposta contraproposta);
}