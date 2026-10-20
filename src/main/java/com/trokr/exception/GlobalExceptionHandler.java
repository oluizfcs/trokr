package com.trokr.exception;

import java.net.URI;
import java.time.Instant;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.validation.FieldError;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private ProblemDetail problema(HttpStatus status, String titulo, String codigo, String detalhe, HttpServletRequest request) {
        ProblemDetail pd = ProblemDetail.forStatusAndDetail(status, detalhe);
        pd.setTitle(titulo);
        pd.setType(URI.create("http://localhost:8090/erros/" + codigo.toLowerCase()));
        pd.setInstance(URI.create(request.getRequestURI()));
        pd.setProperty("codigo", codigo);
        pd.setProperty("timestamp", Instant.now());
        return pd;
    }

    @ExceptionHandler(TransicaoInvalidaException.class)
    public ProblemDetail transicaoInvalida(TransicaoInvalidaException ex, HttpServletRequest req) {
        return problema(HttpStatus.CONFLICT, "Transição inválida", "TRANSICAO_INVALIDA", ex.getMessage(), req);
    }

    // @Override
    // protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
    //     List<FieldError> erros = ex.getBindingResult().getFieldErrors();
    //     problema(HttpStatus.BAD_REQUEST, "Dados inválidos", "VALIDACAO", "detalhe", null);
    // }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleNotFound(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("erro", ex.getMessage()));
    }

    @ExceptionHandler (ObjectOptimisticLockingFailureException.class)
    public ResponseEntity<Void> handleConflict(ObjectOptimisticLockingFailureException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
}
