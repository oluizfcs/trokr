package com.trokr.dto;

import com.trokr.model.CategoriaItem;
import com.trokr.model.Experiencia;
import com.trokr.model.Item;
import com.trokr.model.NivelExperiencia;
import com.trokr.model.Produto;
import com.trokr.model.Servico;

import java.time.LocalDateTime;

/**
 * Dados de saída de um Item. Os dados do dono são achatados aqui
 * (usuarioId/usuarioNome) para manter o DTO simples, em vez de criar mais
 * uma classe aninhada só para isso.
 */
public record ItemResponseDTO(
    Long id,
    String titulo,
    String descricao,
    Long usuarioId,
    String usuarioNome,
    LocalDateTime dataCriacao,
    CategoriaItem categoria,
    Integer gramas,
    Integer comprimentoCm,
    Integer larguraCm,
    Integer alturaCm,
    Integer duracaoMinutos,
    NivelExperiencia nivel,
    Integer lotacaoPessoas
) {
    public static ItemResponseDTO fromEntity(Item item) {
        switch (item.getCategoria()) {
            case PRODUTO:
                Produto p = (Produto) item;
                return new ItemResponseDTO(
                    p.getId(),
                    p.getTitulo(),
                    p.getDescricao(),
                    p.getUsuarioProprietario().getId(),
                    p.getUsuarioProprietario().getNome(),
                    p.getDataCriacao(),
                    p.getCategoria(),
                    p.getGramas(),
                    p.getDimensoes().comprimentoCm(),
                    p.getDimensoes().larguraCm(),
                    p.getDimensoes().alturaCm(),
                    null,
                    null,
                    null
                );
            case SERVICO:
                Servico s = (Servico) item;
                return new ItemResponseDTO(
                    s.getId(),
                    s.getTitulo(),
                    s.getDescricao(),
                    s.getUsuarioProprietario().getId(),
                    s.getUsuarioProprietario().getNome(),
                    s.getDataCriacao(),
                    s.getCategoria(),
                    null,
                    null,
                    null,
                    null,
                    s.getDuracaoMinutos(),
                    null,
                    null
                );
            case EXPERIENCIA:
                Experiencia e = (Experiencia) item;
                return new ItemResponseDTO(
                    e.getId(),
                    e.getTitulo(),
                    e.getDescricao(),
                    e.getUsuarioProprietario().getId(),
                    e.getUsuarioProprietario().getNome(),
                    e.getDataCriacao(),
                    e.getCategoria(),
                    null,
                    null,
                    null,
                    null,
                    e.getDuracaoMinutos(),
                    e.getNivel(),
                    e.getLotacaoPessoas()
                );
            default:
                throw new IllegalStateException("item dto");
        }
    }
}
