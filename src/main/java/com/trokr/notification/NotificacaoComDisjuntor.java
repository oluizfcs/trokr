package com.trokr.notification;

import java.time.Duration;
import java.time.Instant;

import com.trokr.service.LogEventoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class NotificacaoComDisjuntor implements NotificacaoPort {
    private final NotificacaoPort proximo;
    private final int limiteFalhas;
    private final Duration tempoAberto;
    private final LogEventoService logEventoService;
    private int falhasSeguidas = 0;
    private Instant abertoAte = null;

    // TODO: testar antes de implementar estado meio aberto
    @Override
    public synchronized void notificar(Long usuarioId, String mensagem) {
        if (abertoAte != null) { // ABERTO (notificações não são enviadas)
            registrarPendente(usuarioId, mensagem);
            return;
        }
        try {
            proximo.notificar(usuarioId, mensagem);
            falhasSeguidas = 0;
        } catch (NotificacaoIndisponivelException ex) {
            if (++falhasSeguidas >= limiteFalhas) {
                abertoAte = Instant.now().plus(tempoAberto);
                log.warn("Disjuntor ABERTO até {}", abertoAte);
            }
            throw ex; // o retry, por fora, decide
        }
    }

    private void registrarPendente(Long usuarioId, String mensagem) {
        log.warn("Disjuntor aberto: notificação para o usuário {} ficou pendente", usuarioId);
        try {
            logEventoService.registrarInfo("NOTIFICACAO_PENDENTE", this.getClass().getName(), null, usuarioId);
        } catch (Exception ex) {
            log.error("Não foi possível registrar a pendência", ex);
        }
    }
}