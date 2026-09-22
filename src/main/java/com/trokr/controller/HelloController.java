package com.trokr.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.trokr.dto.LogEventoResponseDTO;
import com.trokr.model.LogEvento;
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

    @GetMapping("/eventos")
    public List<LogEventoResponseDTO> listar(@RequestParam String tipo) {
        List<LogEvento> eventos = registroDeEventos.buscarPorTipo(tipo);
        return eventos.stream()
        .map(LogEventoResponseDTO::fromDocumento)
        .toList();
    }
}
