package com.trokr.notification;

import java.time.Duration;

public class NotificacaoProperties {
    public int esperaInicialMs = 200;
    public int maxTentativas = 3;
    public Duration tempoAberto = Duration.ofSeconds(30);
    public int limiteFalhas = 5;

    public int esperaInicialMs() {return esperaInicialMs;}
    public int maxTentativas() {return maxTentativas;}
    public Duration tempoAberto() {return tempoAberto;}
    public int limiteFalhas() {return limiteFalhas;}
}
