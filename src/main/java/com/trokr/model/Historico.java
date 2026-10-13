package com.trokr.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity 
@Table (name = "historico")
@Getter 
@Setter 
@NoArgsConstructor 
@AllArgsConstructor 
public class Historico {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "proposta_id", nullable = false)
    private Proposta proposta;

    @Column(nullable = false)
    private String motivo;

    @Enumerated(EnumType.STRING)
    private StatusProposta estadoAnterior;

    @Enumerated(EnumType.STRING)
    private StatusProposta estadoNovo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @CreationTimestamp 
    @Column(nullable = false, updatable = false)
    private LocalDateTime data;
}
