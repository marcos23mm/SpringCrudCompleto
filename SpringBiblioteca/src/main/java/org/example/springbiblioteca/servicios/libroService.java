package org.example.springbiblioteca.servicios;

import org.example.springbiblioteca.modelo.Libro;
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

    public List<Libro> obtenerTodos() {
        return libroRepository.findAll();
    }

    public Optional<Libro> obtenerPorIsbn(String isbn) {
        return libroRepository.findById(isbn);
    }

    public Libro guardar(Libro libro) {
        return libroRepository.save(libro);
    }

    public void eliminar(String isbn) {
        libroRepository.deleteById(isbn);
    }

}
