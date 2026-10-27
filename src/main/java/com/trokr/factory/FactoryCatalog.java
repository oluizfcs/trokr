package com.trokr.factory;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.trokr.dto.ItemRequestDTO;
import com.trokr.model.CategoriaItem;
import com.trokr.model.Item;
import com.trokr.model.Usuario;

@Component
public class FactoryCatalog {
    private final Map<CategoriaItem, ItemFactory> factories;

    public FactoryCatalog(List<ItemFactory> lista) {
        factories = lista.stream().collect(
            Collectors.toMap(ItemFactory::categoria, f -> f)
        );
    }

    public Item criar(ItemRequestDTO dados, Usuario proprietario) {
        return factories.get(CategoriaItem.valueOf(dados.categoria())).criar(dados, proprietario);
    }
}
