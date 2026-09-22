package com.trokr.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trokr.dto.PropostaRequestDTO;
import com.trokr.dto.PropostaResponseDTO;
import com.trokr.model.Proposta;
import com.trokr.service.ItemService;
import com.trokr.service.PropostaService;
import com.trokr.service.UsuarioService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController 
@RequestMapping("/propostas")
@RequiredArgsConstructor
public class PropostaController {
    private final PropostaService propostaService;
    private final ItemService itemService;
    private final UsuarioService usuarioService;

    @GetMapping
    public List<PropostaResponseDTO> listarPropostasAtivas() {
        return propostaService.listarPropostasAtivas().stream()
                .map(PropostaResponseDTO::fromEntity)
                .toList();
    }

    @GetMapping("/{id}/contrapropostas")
    public List<PropostaResponseDTO> listarContrapropostas(@PathVariable Long id) {
        return propostaService.listarContrapropostas(id).stream()
                .map(PropostaResponseDTO::fromEntity)
                .toList();
    }

    @GetMapping("/{id}/enviar")
    public ResponseEntity<Void> enviarRascunho(@PathVariable Long id) {
        propostaService.enviarRascunho(id);
        return ResponseEntity.ok().build();
    }

    // TODO: verificar se usuário é admin
    @GetMapping("/{id}/aprovar")
    public ResponseEntity<Void> aprovarProposta(@PathVariable Long id) {
        propostaService.aprovar(id);
        return ResponseEntity.ok().build();
    }

    // TODO: verificar se usuário é admin
    @GetMapping("/{id}/reprovar")
    public ResponseEntity<Void> reprovarProposta(@PathVariable Long id) {
        propostaService.reprovar(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/selecionar/{contraId}")
    public ResponseEntity<Void> selecionarContraproposta(@PathVariable Long id, @PathVariable Long contraId) {
        propostaService.selecionarContraproposta(id, contraId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/confirmar")
    public ResponseEntity<Void> confirmarTroca(@PathVariable Long id) {
        propostaService.confirmarTroca(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/cancelar")
    public ResponseEntity<Void> cancelar(@PathVariable Long id) {
        propostaService.cancelar(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/desistir")
    public ResponseEntity<Void> desistir(@PathVariable Long id) {
        propostaService.desistir(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/recusar")
    public ResponseEntity<Void> recusar(@PathVariable Long id) {
        propostaService.recusar(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public PropostaResponseDTO buscarPorId(@PathVariable Long id) {
        return PropostaResponseDTO.fromEntity(propostaService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<PropostaResponseDTO> criar(@Valid @RequestBody PropostaRequestDTO dto) {
        Proposta proposta = new Proposta();
        proposta.setDescricao(dto.descricao());
        proposta.setItem(itemService.buscarPorId(dto.itemId()));
        proposta.setUsuario(usuarioService.buscarPorId(dto.usuarioId()));

        if(dto.propostaId() == null) {
            proposta.setProposta(null);
            proposta.inicializarProposta();
        } else {
            proposta.setProposta(propostaService.buscarPorId(dto.propostaId()));
            proposta.inicializarContraproposta();
        }
        
        Proposta salva = propostaService.criar(proposta);

        return ResponseEntity.status(HttpStatus.CREATED).body(PropostaResponseDTO.fromEntity(salva));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PropostaResponseDTO> atualizar(@PathVariable Long id, @Valid @RequestBody PropostaRequestDTO dto) {
        Proposta atualizada = new Proposta();
        atualizada.setDescricao(dto.descricao());
        atualizada.setItem(itemService.buscarPorId(dto.itemId()));
        Proposta salva = propostaService.atualizar(id, atualizada);

        return ResponseEntity.status(HttpStatus.OK).body(PropostaResponseDTO.fromEntity(salva));
    }
}
