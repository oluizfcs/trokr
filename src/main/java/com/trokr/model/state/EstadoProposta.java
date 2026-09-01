package com.trokr.model.state;

import com.trokr.model.Proposta;
import com.trokr.model.Proposta.EstadosProposta;

public interface EstadoProposta {
    EstadosProposta getStatus();

    // Rascunho ---> Homologacao
    void enviarParaHomologacao(Proposta proposta);

    // Homologacao ---> Ativa
    void homologarRascunho(Proposta proposta);

    // Homologacao ---> Rascunho
    void reprovarRascunho(Proposta proposta);

    // [Homologacao, Ativa] ---> Rascunho
    void editarProposta(Proposta proposta);

    // Ativa ---> Negociada
    void aceitarContraproposta(Proposta proposta);

    // Negociada ---> Finalizada
    void finalizar(Proposta proposta);

    // [Rascunho, Homologacao, Ativa] ---> Cancelada
    void cancelar(Proposta proposta);
}