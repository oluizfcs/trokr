package com.trokr.dto;

import java.time.LocalDateTime;

import com.trokr.model.Item;
import com.trokr.model.Proposta;
import com.trokr.model.Usuario;
import com.trokr.model.state.contraproposta.EstadoContraproposta;
import com.trokr.model.state.proposta.EstadoProposta;

public record PropostaResponseDTO(
    Long id,
    String descricao,
    Item item,
    Usuario usuario,
    Proposta proposta,
    LocalDateTime dataCriacao,
    EstadoProposta statusProposta,
    EstadoContraproposta statusContraproposta
) {
    public static PropostaResponseDTO fromEntity(Proposta proposta) {
        return new PropostaResponseDTO(
            proposta.getId(),
            proposta.getDescricao(),
            proposta.getItem(),
            proposta.getUsuario(),
            proposta.getProposta(),
            proposta.getDataCriacao(),
            proposta.getStatusProposta(), proposta.getStatusContraproposta()
        );
    }
}
