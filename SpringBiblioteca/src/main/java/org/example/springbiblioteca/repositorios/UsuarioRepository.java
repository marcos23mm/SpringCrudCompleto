package org.example.springbiblioteca.repositorios;

import org.example.springbiblioteca.modelo.usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<usuario, String> {

    Optional<usuario> findByDni(String dni);
    void deleteByDni(String dni);

}
