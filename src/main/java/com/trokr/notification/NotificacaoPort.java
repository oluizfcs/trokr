package com.trokr.notification;

import org.springframework.stereotype.Component;

@Component
public interface NotificacaoPort {
    void notificar(Long usuarioId, String mensagem);
}
