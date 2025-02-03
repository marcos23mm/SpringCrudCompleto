package org.example.springbiblioteca.repositorios;

import org.example.springbiblioteca.modelo.Ejemplar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EjemplarRepository extends JpaRepository<Ejemplar, Long> {
    //Metodos personalizados
}
