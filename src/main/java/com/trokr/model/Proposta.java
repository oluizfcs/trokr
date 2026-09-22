package com.trokr.model;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.trokr.model.state.contraproposta.EstadoCPCancelada;
import com.trokr.model.state.contraproposta.EstadoCPEmAnalise;
import com.trokr.model.state.contraproposta.EstadoCPFinalizada;
import com.trokr.model.state.contraproposta.EstadoCPNegociada;
import com.trokr.model.state.contraproposta.EstadoCPRascunho;
import com.trokr.model.state.contraproposta.EstadoCPRecusada;
import com.trokr.model.state.contraproposta.EstadoContraproposta;
import com.trokr.model.state.proposta.EstadoAtiva;
import com.trokr.model.state.proposta.EstadoCancelada;
import com.trokr.model.state.proposta.EstadoFinalizada;
import com.trokr.model.state.proposta.EstadoHomologacao;
import com.trokr.model.state.proposta.EstadoNegociada;
import com.trokr.model.state.proposta.EstadoProposta;
import com.trokr.model.state.proposta.EstadoRascunho;

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
import jakarta.persistence.OneToMany;
import jakarta.persistence.PostLoad;
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

    @Enumerated(EnumType.STRING)
    private StatusProposta status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "proposta_id", nullable = true)
    private Proposta proposta;

    @JsonIgnore
    @OneToMany(mappedBy = "proposta")
    private List<Proposta> contrapropostas;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime dataCriacao;

    @Transient
    private EstadoProposta statusProposta;

    @Transient
    private EstadoContraproposta statusContraproposta;

    public void inicializarProposta() {
        status = StatusProposta.RASCUNHO;
        statusProposta = new EstadoRascunho();
    }

    public void inicializarContraproposta() {
        status = StatusProposta.CP_RASCUNHO;
        statusContraproposta = new EstadoCPRascunho();
    }

    public void mudarEstadoPara(EstadoProposta novoEstado) {
        this.statusProposta = novoEstado;
        this.status = novoEstado.getStatus();
    }

    public void mudarEstadoPara(EstadoContraproposta novoEstado) {
        this.statusContraproposta = novoEstado;
        this.status = novoEstado.getStatus();
    }

    public boolean isRaiz() {
        return proposta == null;
    }

    public Proposta propostaRelacionada() {
        if (isRaiz()) {
            return contrapropostas.stream()
                .filter(cp -> cp.getStatus() == StatusProposta.CP_NEGOCIADA)
                .findFirst()
                .orElse(null);
        }

        return proposta;
    }

    public EstadoProposta comoRaiz() {
        if (isRaiz()) {
            return statusProposta;
        }
        return null;
    }

    public EstadoContraproposta comoContra() {
        if (isRaiz()) {
            return null;
        }
        return statusContraproposta;
    }

    @PostLoad
    private void carregarEstado() {
        if(isRaiz()) {
            statusContraproposta = null;
        } else {
            statusProposta = null;
        }

        switch (status) {
            case ATIVA         -> statusProposta = new EstadoAtiva();
            case CANCELADA     -> statusProposta = new EstadoCancelada();
            case HOMOLOGACAO   -> statusProposta = new EstadoHomologacao();
            case NEGOCIADA     -> statusProposta = new EstadoNegociada();
            case RASCUNHO      -> statusProposta = new EstadoRascunho();
            case FINALIZADA    -> statusProposta = new EstadoFinalizada();
            case CP_CANCELADA  -> statusContraproposta = new EstadoCPCancelada();
            case CP_EM_ANALISE -> statusContraproposta = new EstadoCPEmAnalise();
            case CP_FINALIZADA -> statusContraproposta = new EstadoCPFinalizada();
            case CP_NEGOCIADA  -> statusContraproposta = new EstadoCPNegociada();
            case CP_RASCUNHO   -> statusContraproposta = new EstadoCPRascunho();
            case CP_RECUSADA   -> statusContraproposta = new EstadoCPRecusada();
            default -> throw new IllegalArgumentException("Unexpected value: " + status);
        }
    }
}
