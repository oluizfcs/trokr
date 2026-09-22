package com.trokr.model;

import java.time.LocalDateTime;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document("logEventos")
@NoArgsConstructor
@AllArgsConstructor 
@Getter
@Setter
public class LogEvento {
    private String tipo;
    private String mensagem;
    private String nivel;
    private String origem;
    private Object payload;
    private LocalDateTime timestamp;
    private Usuario usuarioId;
}
