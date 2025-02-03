package org.example.springbiblioteca.repositorios;

import org.example.springbiblioteca.modelo.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroRepository extends JpaRepository<Libro, String> {
    // Aquí puedes definir métodos de consulta personalizados si es necesario.
}
