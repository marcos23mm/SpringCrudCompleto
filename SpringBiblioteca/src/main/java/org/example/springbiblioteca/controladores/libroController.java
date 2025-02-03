package org.example.springbiblioteca.controladores;

import jakarta.validation.Valid;
import org.example.springbiblioteca.modelo.libro;
import org.example.springbiblioteca.servicios.libroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/libros")
public class libroController {

    private final libroService libroService;

    public libroController(libroService libroService) {
        this.libroService = libroService;
    }

    // Obtener todos los libros
    @GetMapping
    public List<libro> listar() {
        return libroService.obtenerTodos();
    }

    // Obtener un libro por ISBN
    @GetMapping("/{isbn}")
    public ResponseEntity<libro> obtener(@PathVariable String isbn) {
        Optional<libro> libro = libroService.obtenerPorIsbn(isbn);
        return libro.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear un nuevo libro
    @PostMapping
    public ResponseEntity<libro> crear(@Valid @RequestBody libro libro) {
        libro libroGuardado = libroService.guardar(libro);
        return ResponseEntity.ok(libroGuardado);
    }

    // Actualizar un libro existente
    @PutMapping("/{isbn}")
    public ResponseEntity<libro> actualizar(@PathVariable String isbn, @Valid @RequestBody libro libro) {
        if (!libroService.obtenerPorIsbn(isbn).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        libro.setIsbn(isbn);
        libro libroGuardado = libroService.guardar(libro);
        return ResponseEntity.ok(libroGuardado);
    }

    // Eliminar un libro
    @DeleteMapping("/{isbn}")
    public ResponseEntity<Void> eliminar(@PathVariable String isbn) {
        if (!libroService.obtenerPorIsbn(isbn).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        libroService.eliminar(isbn);
        return ResponseEntity.noContent().build();
    }
}
