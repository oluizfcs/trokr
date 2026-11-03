package com.trokr.notification;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class NotificacaoSimuladaAdapter implements NotificacaoPort {

    public final NotificacaoSimulacao simulacao;

    @Override
    public void notificar(Long usuarioId, String mensagem) {
        if (simulacao.foraDoAr()) {
            System.out.println("Serviço de notifcação fora do ar");
            throw new NotificacaoIndisponivelException("Serviço de notificação fora do ar");
        }
        System.out.println(mensagem);
    }
    
}
