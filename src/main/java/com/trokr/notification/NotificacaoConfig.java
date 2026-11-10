package com.trokr.notification;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.trokr.service.LogEventoService;

@Configuration
public class NotificacaoConfig {
    @Bean
    @Primary
    public NotificacaoPort notificacaoPort(NotificacaoSimuladaAdapter adaptador,
            NotificacaoProperties props, LogEventoService logEventoService) {
        // de fora para dentro: retry -> disjuntor -> adaptador
        return new NotificacaoComRetry(
                new NotificacaoComDisjuntor(adaptador, props.limiteFalhas(),
                        props.tempoAberto(), logEventoService),
                props.maxTentativas(), props.esperaInicialMs());
    }
}
