package com.trokr.factory;

import com.trokr.dto.ItemRequestDTO;
import com.trokr.model.CategoriaItem;
import com.trokr.model.Item;
import com.trokr.model.Usuario;

public interface ItemFactory {
    CategoriaItem categoria();
    Item criar(ItemRequestDTO dados, Usuario proprietario);
}
