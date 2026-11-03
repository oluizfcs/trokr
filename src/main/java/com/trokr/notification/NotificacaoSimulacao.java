package com.trokr.notification;

import java.util.concurrent.atomic.AtomicBoolean;

import org.springframework.stereotype.Component;

@Component
public class NotificacaoSimulacao {
    private final AtomicBoolean foraDoAr = new AtomicBoolean(false);

    public boolean foraDoAr() { return foraDoAr.get(); };
    public void derrubar() { foraDoAr.set(true); };
    public void religar() { foraDoAr.set(false); };
}
