package com.trokr.notification;

import org.springframework.stereotype.Component;

import com.trokr.config.AtrasoDemo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotificacaoSimuladaAdapter implements NotificacaoPort {

    private final NotificacaoProperties props;
    public final SimulacaoNotificacao simulacao;

    @Override
    public void notificar(Long usuarioId, String mensagem) {
        if (simulacao.foraDoAr()) {
            throw new NotificacaoIndisponivelException("Serviço de notificação fora do ar");
        }

        new AtrasoDemo(props.getLatenciaMs()).aplicar();

        if(Math.random() < props.getTaxaFalha()) {
            throw new NotificacaoIndisponivelException("Notificação falhou. chance: " + props.getTaxaFalha() * 100 + "%");
        }

        log.info("Notificação enviada com sucesso para {}", usuarioId);
    }
}
