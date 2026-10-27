package com.trokr.model;

import jakarta.persistence.Embeddable;

@Embeddable
public record Dimensoes(
    Integer comprimentoCm,
    Integer larguraCm,
    Integer alturaCm
) {
    public Integer calcularVolume() {
        return comprimentoCm * larguraCm * alturaCm;
    }
}
