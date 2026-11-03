package com.trokr.notification;

import org.springframework.stereotype.Component;

import com.trokr.config.AtrasoDemo;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor 
public class NotificacaoComRetry implements NotificacaoPort {

    private final NotificacaoPort notificacaoPort;

    @Override
    public void notificar(Long usuarioId, String mensagem) {
        long espera = NotificacaoProperties.esperaInicialMs;
        for (int tentativa = 1; ; tentativa++) {
            try {
                notificacaoPort.notificar(usuarioId, mensagem);
                return;
            } catch (NotificacaoIndisponivelException ex) {
                if (tentativa >= NotificacaoProperties.maxTentativas) throw ex;
                System.out.println("Tentativa " + tentativa + " para " + usuarioId + " falhou");
                new AtrasoDemo(espera).aplicar();
                espera *= 2;
            }
        }
    }
    
}
