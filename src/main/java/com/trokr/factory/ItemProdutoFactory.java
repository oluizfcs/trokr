package com.trokr.factory;

import org.springframework.stereotype.Component;

import com.trokr.dto.ItemRequestDTO;
import com.trokr.model.CategoriaItem;
import com.trokr.model.Dimensoes;
import com.trokr.model.Item;
import com.trokr.model.Produto;
import com.trokr.model.Usuario;

@Component
public class ItemProdutoFactory implements ItemFactory {

    @Override
    public CategoriaItem categoria() {
        return CategoriaItem.PRODUTO;
    }

    @Override
    public Item criar(ItemRequestDTO dados, Usuario proprietario) {
        return Produto.builder()
            .titulo(dados.titulo())
            .descricao(dados.descricao())
            .usuarioProprietario(proprietario)
            .gramas(dados.gramas())
            .dimensoes(new Dimensoes(
                dados.comprimentoCm(),
                dados.larguraCm(),
                dados.alturaCm()))
            .build();
    }
    
}
