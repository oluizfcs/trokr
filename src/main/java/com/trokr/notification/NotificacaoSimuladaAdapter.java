package com.trokr.notification;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.trokr.config.AtrasoDemo;
import com.trokr.service.LogEventoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotificacaoSimuladaAdapter implements NotificacaoPort {

    private final NotificacaoProperties props;
    public final SimulacaoNotificacao simulacao;
    private final LogEventoService logEventoService;

    @Override
    public void notificar(Long usuarioId, String mensagem) {
        if (simulacao.foraDoAr()) {
            throw new NotificacaoIndisponivelException("Serviço de notificação fora do ar");
        }

        new AtrasoDemo(props.getLatenciaMs()).aplicar();

        if(Math.random() < props.getTaxaFalha()) {
            throw new NotificacaoIndisponivelException("Falha simulada, chance: " + Math.round(props.getTaxaFalha() * 100) + "%");
        }

        salvarLogMongo(usuarioId, mensagem);
        log.info("Notificação enviada com sucesso para {}", usuarioId);
    }

    private void salvarLogMongo(Long usuarioId, String mensagem) {
        Map<String, Object> payload = new HashMap<>();

        payload.put("usuarioId", usuarioId);
        payload.put("mensagem", mensagem);

        logEventoService.registrarInfo(
            "NOTIFICACAO_ENVIADA",
            this.getClass().getName(),
            payload,
            usuarioId
        );
    }
}
