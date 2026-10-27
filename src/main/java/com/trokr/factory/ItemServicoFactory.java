package com.trokr.factory;

import org.springframework.stereotype.Component;

import com.trokr.dto.ItemRequestDTO;
import com.trokr.model.CategoriaItem;
import com.trokr.model.Item;
import com.trokr.model.Servico;
import com.trokr.model.Usuario;

@Component
public class ItemServicoFactory implements ItemFactory {

    @Override
    public CategoriaItem categoria() {
        return CategoriaItem.SERVICO;
    }

    @Override
    public Item criar(ItemRequestDTO dados, Usuario proprietario) {
        return Servico.builder()
            .titulo(dados.titulo())
            .descricao(dados.descricao())
            .usuarioProprietario(proprietario)
            .duracaoMinutos(dados.duracaoMinutos())
            .build();
    }
    
}
