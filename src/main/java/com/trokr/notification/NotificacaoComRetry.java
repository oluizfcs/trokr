package com.trokr.notification;

import org.springframework.stereotype.Component;

import com.trokr.config.AtrasoDemo;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor 
public class NotificacaoComRetry implements NotificacaoPort {

    private final NotificacaoPort notificacaoPort;
    private final int maxTentativas;
    private final long esperaInicialMs;

    @Override
    public void notificar(Long usuarioId, String mensagem) {
        long espera = esperaInicialMs;
        for (int tentativa = 1; ; tentativa++) {
            try {
                notificacaoPort.notificar(usuarioId, mensagem);
                return;
            } catch (NotificacaoIndisponivelException ex) {
                if (tentativa >= maxTentativas) throw ex;
                System.out.println("Tentativa " + tentativa + " para " + usuarioId + " falhou");
                new AtrasoDemo(espera).aplicar();
                espera *= 2;
            }
        }
    }
    
}
