package com.trokr.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
@Entity
@DiscriminatorValue("PRODUTO")
public class Produto extends Item {

    @Column(nullable = true)
    private Integer gramas;

    @Embedded
    @Column(nullable = true)
    private Dimensoes dimensoes;

    @Override
    public CategoriaItem getCategoria() {
        return CategoriaItem.PRODUTO;
    }
}
