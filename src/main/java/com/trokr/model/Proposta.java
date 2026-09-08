package com.trokr.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.trokr.model.state.contraproposta.EstadoCPRascunho;
import com.trokr.model.state.contraproposta.EstadoContraproposta;
import com.trokr.model.state.proposta.EstadoProposta;
import com.trokr.model.state.proposta.EstadoRascunho;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "proposta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Proposta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String descricao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "proposta_id", nullable = true)
    private Proposta proposta;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime dataCriacao;

    @Transient
    private EstadoProposta statusProposta;

    @Transient
    private EstadoContraproposta statusContraproposta;

    public void inicializarProposta() {
        statusProposta = new EstadoRascunho();
    }

    public void inicializarContraproposta() {
        statusContraproposta = new EstadoCPRascunho();
    }

    public void mudarEstadoPara(EstadoProposta novoEstado) {
        this.statusProposta = novoEstado;
    }

    public void mudarEstadoPara(EstadoContraproposta novoEstado) {
        this.statusContraproposta = novoEstado;
    }
}
