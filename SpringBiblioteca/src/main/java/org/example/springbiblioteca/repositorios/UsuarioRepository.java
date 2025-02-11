package org.example.springbiblioteca.repositorios;

import org.example.springbiblioteca.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {

    Optional<Usuario> findByDni(String dni);
    void deleteByDni(String dni);

}
