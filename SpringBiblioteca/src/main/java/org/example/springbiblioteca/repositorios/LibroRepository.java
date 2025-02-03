package org.example.springbiblioteca.repositorios;

import org.example.springbiblioteca.modelo.libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroRepository extends JpaRepository<libro, String> {
    // Aquí puedes definir métodos de consulta personalizados si es necesario.
}
