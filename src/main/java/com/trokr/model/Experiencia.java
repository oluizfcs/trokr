package com.trokr.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@DiscriminatorValue("EXPERIENCIA")
public class Experiencia extends Item {

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private NivelExperiencia nivel;

    @Column(nullable = true)
    private Integer duracaoMinutos;

    @Column(nullable = true)
    private Integer lotacaoPessoas;

    @Override
    public CategoriaItem getCategoria() {
        return CategoriaItem.EXPERIENCIA;
    }
    
}
