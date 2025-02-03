package org.example.springbiblioteca.servicios;

import org.example.springbiblioteca.modelo.libro;
import org.example.springbiblioteca.repositorios.LibroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class libroService {

    private final LibroRepository libroRepository;

    public libroService(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    public List<libro> obtenerTodos() {
        return libroRepository.findAll();
    }

    public Optional<libro> obtenerPorIsbn(String isbn) {
        return libroRepository.findById(isbn);
    }

    public libro guardar(libro libro) {
        return libroRepository.save(libro);
    }

    public void eliminar(String isbn) {
        libroRepository.deleteById(isbn);
    }
}
