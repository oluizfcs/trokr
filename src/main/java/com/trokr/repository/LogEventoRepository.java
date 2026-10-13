package com.trokr.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.trokr.model.LogEvento;
import com.trokr.model.NivelEvento;

public interface LogEventoRepository extends MongoRepository<LogEvento, String> {
    List<LogEvento> findByTipo(String tipo);
    List<LogEvento> findByNivelAndTimestampBetween(NivelEvento nivel, LocalDateTime inicio, LocalDateTime fim);
}
