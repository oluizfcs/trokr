package com.trokr.service.credito;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.trokr.model.CategoriaItem;
import com.trokr.model.Item;

@Service
public class CalculadoraCreditoService {
    private final Map<CategoriaItem, EstrategiaCredito> estrategias;

    public CalculadoraCreditoService(List<EstrategiaCredito> todas) {
        estrategias = todas.stream()
            .collect(Collectors.toMap(EstrategiaCredito::categoria, e -> e));
    }

    public int calcular(Item item) {
        EstrategiaCredito estrategia = estrategias.get(item.getCategoria());
        if (estrategia == null) {
            throw new IllegalStateException("Sem estratégia para " + item.getCategoria());
        }
        return estrategia.calcular(item);
    }
}
