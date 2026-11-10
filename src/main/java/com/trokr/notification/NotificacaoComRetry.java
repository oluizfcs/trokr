package com.trokr.notification;

import org.springframework.stereotype.Component;

import com.trokr.config.AtrasoDemo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
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
                log.warn("Tentativa {} para {} falhou. Nova tentativa em {} ms", tentativa, usuarioId, espera);
                new AtrasoDemo(espera).aplicar();
                espera *= 2;
            }
        }
    }
    
}
