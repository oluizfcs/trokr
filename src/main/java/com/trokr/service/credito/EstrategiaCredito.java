package com.trokr.service.credito;

import com.trokr.model.CategoriaItem;
import com.trokr.model.Item;

public interface EstrategiaCredito {
    CategoriaItem categoria();
    int calcular(Item item);   
}