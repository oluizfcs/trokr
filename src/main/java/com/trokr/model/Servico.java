package com.trokr.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
@Entity
@DiscriminatorValue("SERVICO")
public class Servico extends Item {

    @Column(nullable = true)
    private Integer duracaoMinutos;

    @Override
    public CategoriaItem getCategoria() {
        return CategoriaItem.SERVICO;
    }
    
}
