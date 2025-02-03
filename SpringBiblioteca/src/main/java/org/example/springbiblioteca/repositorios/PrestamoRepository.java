package org.example.springbiblioteca.repositorios;

import org.example.springbiblioteca.modelo.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {
    // No es necesario redefinir findById, JpaRepository ya lo maneja con Long
}

