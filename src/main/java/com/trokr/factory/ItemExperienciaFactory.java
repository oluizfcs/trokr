package com.trokr.factory;

import org.springframework.stereotype.Component;

import com.trokr.dto.ItemRequestDTO;
import com.trokr.model.CategoriaItem;
import com.trokr.model.Experiencia;
import com.trokr.model.Item;
import com.trokr.model.NivelExperiencia;
import com.trokr.model.Usuario;

@Component
public class ItemExperienciaFactory implements ItemFactory {

    @Override
    public CategoriaItem categoria() {
        return CategoriaItem.EXPERIENCIA;
    }

    @Override
    public Item criar(ItemRequestDTO dados, Usuario proprietario) {
        return Experiencia.builder()
            .titulo(dados.titulo())
            .descricao(dados.descricao())
            .usuarioProprietario(proprietario)
            .nivel(NivelExperiencia.valueOf(dados.nivel()))
            .duracaoMinutos(dados.duracaoMinutos())
            .lotacaoPessoas(dados.lotacaoPessoas())
            .build();
    }
    
}
