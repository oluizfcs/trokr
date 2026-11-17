package com.trokr.controller;

import java.util.List;

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
    public List<LogEventoResponseDTO> listarPorTipo(@RequestParam(defaultValue = "") String tipo, @RequestParam(defaultValue = "") String nivel) {
        List<LogEvento> eventos = logEventoService.buscar(tipo, nivel);
        return eventos.stream().map(LogEventoResponseDTO::fromDocumento).toList();
    }
}
