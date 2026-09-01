package com.trokr.model;

import com.trokr.model.state.EstadoProposta;
import com.trokr.model.state.EstadoRascunho;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    private String descricao;
    private Item item;
    private Usuario usuario;
    private EstadoProposta estadoAtual = new EstadoRascunho();
    private Proposta proposta;

    public void mudarEstadoPara(EstadoProposta novoEstado) {
        this.estadoAtual = novoEstado;
    }

    public enum EstadosProposta {
        RASCUNHO,
        HOMOLOGACAO,
        ATIVA,
        NEGOCIADA,
        FINALIZADA,
        CANCELADA
    }
}
