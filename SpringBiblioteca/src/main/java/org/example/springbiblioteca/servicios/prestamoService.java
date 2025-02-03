package org.example.springbiblioteca.servicios;

import org.example.springbiblioteca.modelo.Prestamo;
import org.example.springbiblioteca.repositorios.PrestamoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class prestamoService {

    private final PrestamoRepository prestamoRepository;

    @Autowired
    public prestamoService(PrestamoRepository prestamoRepository) {
        this.prestamoRepository = prestamoRepository;
    }
    public List<Prestamo> obtenerPrestamos(){
        return prestamoRepository.findAll();
    }



}
