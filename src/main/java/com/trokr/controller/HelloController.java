package com.trokr.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trokr.service.LogEventoService;

import lombok.RequiredArgsConstructor;

// Endpoint de "Hello World" só para validar rapidamente, no Postman/Insomnia,
// que a aplicação está de pé e respondendo — sem precisar de banco de dados.
@RestController
@RequiredArgsConstructor 
public class HelloController {

    public final LogEventoService registroDeEventos;

    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello, Trokr!";
    }

    @GetMapping("/erro")
    public Void lancarErro() {
        throw new RuntimeException("teste");
    }
}
