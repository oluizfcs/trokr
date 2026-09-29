package com.trokr.service.credito;

import org.springframework.stereotype.Component;

import com.trokr.model.CategoriaItem;
import com.trokr.model.Item;

@Component
public class CreditoProduto implements EstrategiaCredito {

    @Override
    public CategoriaItem categoria() {
        return CategoriaItem.PRODUTO;
    }

    @Override
    public int calcular(Item item) {
        return 1;
    }
    
}
