package com.trokr.controller;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trokr.notification.NotificacaoSimulacao;

import lombok.RequiredArgsConstructor;

@RestController 
@RequestMapping ("/demo/notificacao")
@RequiredArgsConstructor 
@ConditionalOnProperty (name = "trokr.demo.endpoints", havingValue = "true")
public class DemoNotificacaoController {
    private final NotificacaoSimulacao simulacao;

    @PatchMapping ("/fora-do-ar")
    public void derrubar() { simulacao.derrubar(); }

    @PatchMapping("/no-ar")
    public void religar() { simulacao.religar(); }

    @GetMapping("/status")
    public boolean status() { return simulacao.foraDoAr(); }
}