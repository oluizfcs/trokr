package com.trokr.notification;

public interface NotificacaoPort {
    void notificar(Long usuarioId, String mensagem);
}
