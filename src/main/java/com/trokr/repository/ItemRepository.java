package com.trokr.repository;

import com.trokr.model.Item;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByUsuarioProprietarioId(Long usuarioId);
    List<Item> findByTituloContainingIgnoreCase(String titulo);
    List<Item> findByTituloContainingIgnoreCaseOrDescricaoContainingIgnoreCase(String titulo, String descricao);
}
