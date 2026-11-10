package com.trokr.notification;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;

@Getter
@Component
public class NotificacaoProperties {

    // simulando perda de pacotes
    private float taxaFalha;
    private int latenciaMs;

    // retry
    private int esperaInicialRetentativaMs;
    private int maxTentativas;

    // disjuntor
    private Duration tempoAbertoDisjuntor;
    private int limiteFalhas;

    public NotificacaoProperties(
        @Value("${trokr.notificacao.taxa-falha:0}") float taxaFalha,
        @Value("${trokr.notificacao.latencia-ms:0}") int latenciaMs,
        @Value("${trokr.notificacao.espera-inicial-retentativa-ms:200}") int esperaInicialRetentativaMs,
        @Value("${trokr.notificacao.max-tentativas:3}") int maxTentativas,
        @Value("${trokr.notificacao.tempoAbertoDisjuntor:30s}") Duration tempoAbertoDisjuntor,
        @Value("${trokr.notificacao.limite-falhas:5}") int limiteFalhas
    ) {
        this.taxaFalha = taxaFalha;
        this.latenciaMs = latenciaMs;
        this.esperaInicialRetentativaMs = esperaInicialRetentativaMs;
        this.maxTentativas = maxTentativas;
    }
}
