package com.trokr.model;

import java.time.LocalDateTime;
import java.util.Map;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
    @Column(nullable = false)
    private String tipo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NivelEvento nivel;

    @Column(nullable = false)
    private String origem;

    @Column(nullable = false)
    private Map<String, Object> payload;

    @CreationTimestamp 
    @Column(nullable = false, updatable = false)
    private LocalDateTime timestamp;

    @Column(nullable = true)
    private Long usuarioId;
}
