package com.trokr.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.trokr.dto.LogEventoResponseDTO;
import com.trokr.model.LogEvento;
import com.trokr.service.LogEventoService;

import lombok.RequiredArgsConstructor;

@RestController 
@RequestMapping ("/logs")
@RequiredArgsConstructor
public class LogEventoController {
    private final LogEventoService logEventoService;

    @GetMapping 
    public List<LogEventoResponseDTO> listarPorTipo(@RequestParam String tipo) {
        List<LogEvento> eventos = logEventoService.buscarPorTipo(tipo);
        return eventos.stream().map(LogEventoResponseDTO::fromDocumento).toList();
    }

    @GetMapping("/teste")
    public ResponseEntity<Void> teste() {

        logEventoService.registrarInfo("teste", "testando persistência", null);

        return ResponseEntity.ok().build();
    }
}
