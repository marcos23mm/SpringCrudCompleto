package org.example.springbiblioteca.repositorios;

import org.example.springbiblioteca.modelo.Ejemplar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface EjemplarRepository extends JpaRepository<Ejemplar, Long> {
    //Metodos personalizados
}
