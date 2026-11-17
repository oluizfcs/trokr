package com.trokr.listener;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import com.trokr.event.TrocaFinalizadaEvent;
import com.trokr.model.Item;
import com.trokr.model.Usuario;
import com.trokr.notification.NotificacaoPort;
import com.trokr.service.LogEventoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotificarUsuarioListener {

    private final NotificacaoPort notificacaoPort;
    private final LogEventoService logEventoService;

    @TransactionalEventListener
    public void enviarConfirmacao(TrocaFinalizadaEvent evento) {
        Long propostaId = evento.getPropostaId();
        Usuario usuarioA = evento.getUsuarioA();
        Usuario usuarioB = evento.getUsuarioB();
        Item itemA = evento.getItemA();
        Item itemB = evento.getItemB();

        notificar(propostaId, usuarioA, usuarioB, itemA, itemB);
        notificar(propostaId, usuarioB, usuarioA, itemB, itemA);
    }

    private void notificar(Long propostaId, Usuario usuarioA, Usuario usuarioB, Item itemA, Item itemB) {

        String notificacao = "Sua troca com o " + usuarioB.getNome() + " foi finalizada. Você recebeu: "
                + itemB.getCategoria() + "(#" + itemB.getId() + ") em troca de: " + itemA.getCategoria() + " (#"
                + itemA.getId() + ")";
        try {
            notificacaoPort.notificar(usuarioA.getId(), notificacao);
        } catch (Exception ex) {
            log.warn("Falha ao notificar finalização de troca (#{}): {}", propostaId, ex.getMessage());
            Map<String, Object> payload = new HashMap<>();
            payload.put("propostaId", propostaId);
            payload.put("notificacao", notificacao);
            logEventoService.registrarWarn("FALHA_NOTIFICACAO", this.getClass().getName(), payload, usuarioA.getId(),ex);
        }
    }
}
