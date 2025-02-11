package org.example.springbiblioteca.servicios;

import org.example.springbiblioteca.modelo.Ejemplar;
import org.example.springbiblioteca.repositorios.EjemplarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ejemplarService {

    private final EjemplarRepository ejemplarRepository;
    @Autowired

    public ejemplarService(EjemplarRepository ejemplarRepository) {
        this.ejemplarRepository = ejemplarRepository;
    }

    public List<Ejemplar> obtenerTodosEjemplares(){
        return ejemplarRepository.findAll();
    }

}
