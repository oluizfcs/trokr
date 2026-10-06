package com.trokr.repository;

import com.trokr.model.Usuario;

import java.util.List;
// import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Optional<Usuario> findByEmail(String email);
    List<Usuario> findByNomeContainingIgnoreCase(String nome);

    @Modifying
    @Query("""
        UPDATE Usuario u
        SET u.saldoCreditos = u.saldoCreditos + :valor
        WHERE u.id = :id
        """)
    int adicionarCreditos(@Param("id") Long id, @Param("valor") int valor);
}
