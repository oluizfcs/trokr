package com.trokr.notification;

import org.springframework.context.annotation.Configuration;

@Configuration
public class NotificacaoConfig {
    public NotificacaoPort notificacaoPort(NotificacaoSimuladaAdapter adaptador, NotificacaoProperties props) {
        return null; // TODO
    }
}
