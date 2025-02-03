package org.example.springbiblioteca.repositorios;

import org.example.springbiblioteca.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {

    Optional<Usuario> findByDni(String dni);
    void deleteByDni(String dni);

}
