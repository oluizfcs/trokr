package com.trokr.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
