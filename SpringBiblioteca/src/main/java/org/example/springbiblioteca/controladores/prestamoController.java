package org.example.springbiblioteca.controladores;

import org.example.springbiblioteca.modelo.Prestamo;
import org.example.springbiblioteca.modelo.Ejemplar;
import org.example.springbiblioteca.modelo.Usuario;
import org.example.springbiblioteca.repositorios.EjemplarRepository;
import org.example.springbiblioteca.repositorios.PrestamoRepository;
import org.example.springbiblioteca.repositorios.UsuarioRepository;
import org.example.springbiblioteca.servicios.prestamoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
    @RequestMapping("/prestamo")

public class prestamoController {

    private final prestamoService prestamoService;
    private final PrestamoRepository prestamoRepository;
    private final EjemplarRepository ejemplarRepository;
    private final UsuarioRepository usuarioRepository;

    public prestamoController(prestamoService prestamoService, PrestamoRepository prestamoRepository, EjemplarRepository ejemplarRepository, UsuarioRepository usuarioRepository) {
        this.prestamoService = prestamoService;
        this.prestamoRepository = prestamoRepository;
        this.ejemplarRepository = ejemplarRepository;
        this.usuarioRepository = usuarioRepository;
    }

    //Obtener prestamos
    @GetMapping
    public List<Prestamo> listarPrestamos() {
        return prestamoService.obtenerPrestamos();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Prestamo> actualizarPrestamo(@PathVariable Long id, @RequestBody Map<String, Object> datos) {
        Optional<Prestamo> prestamoExistenteOpt = prestamoRepository.findById(id);

        if (prestamoExistenteOpt.isPresent()) {
            Prestamo prestamoExistente = prestamoExistenteOpt.get();

            // **1. Obtener el ejemplar antiguo y cambiar su estado a "Disponible"**
            Ejemplar ejemplarAntiguo = prestamoExistente.getEjemplar();
            if (ejemplarAntiguo != null) {
                ejemplarAntiguo.setEstado("Disponible");
                ejemplarRepository.save(ejemplarAntiguo);
            }

            // **2. Buscar el nuevo ejemplar por ID**
            Long ejemplarId = Long.valueOf((Integer) datos.get("ejemplar_id")); // Conversión corregida
            Optional<Ejemplar> ejemplarNuevoOpt = ejemplarRepository.findById(ejemplarId);

            if (ejemplarNuevoOpt.isEmpty()) {
                return ResponseEntity.badRequest().body(null); // 400 si el ejemplar no existe
            }

            Ejemplar ejemplarNuevo = ejemplarNuevoOpt.get();
            ejemplarNuevo.setEstado("Prestado"); // Cambiar estado del nuevo ejemplar
            ejemplarRepository.save(ejemplarNuevo);

            // **3. Actualizar los campos del préstamo**
            prestamoExistente.setFechaInicio(LocalDate.parse((String) datos.get("fecha_inicio")));
            prestamoExistente.setFechaDevolucion(LocalDate.parse((String) datos.get("fecha_devolucion")));
            prestamoExistente.setEjemplar(ejemplarNuevo);

            // **4. Guardar el préstamo actualizado**
            Prestamo prestamoGuardado = prestamoRepository.save(prestamoExistente);

            return ResponseEntity.ok(prestamoGuardado);
        } else {
            return ResponseEntity.notFound().build(); // 404 si el préstamo no existe
        }
    }


    @PostMapping
    public ResponseEntity<Prestamo> registrarPrestamo(@RequestBody Map<String, Object> datos) {
        try {
            // Validar y obtener ejemplar
            Long ejemplarId = datos.get("ejemplar_id") instanceof Integer
                    ? Long.valueOf((Integer) datos.get("ejemplar_id"))
                    : (Long) datos.get("ejemplar_id");
            Optional<Ejemplar> ejemplarOpt = ejemplarRepository.findById(ejemplarId);

            if (ejemplarOpt.isEmpty()) {
                return ResponseEntity.badRequest().body(null); // 400 si el ejemplar no existe
            }

            Ejemplar ejemplar = ejemplarOpt.get();
            if ("Prestado".equalsIgnoreCase(ejemplar.getEstado())) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(null); // 409 si el ejemplar ya está prestado
            }

            // Manejo robusto del usuario_id
            Long usuarioId;
            Object usuarioIdObj = datos.get("usuario_id");

            if (usuarioIdObj instanceof Integer) {
                usuarioId = Long.valueOf((Integer) usuarioIdObj);
            } else if (usuarioIdObj instanceof String) {
                usuarioId = Long.valueOf((String) usuarioIdObj);
            } else if (usuarioIdObj instanceof Long) {
                usuarioId = (Long) usuarioIdObj;
            } else {
                return ResponseEntity.badRequest().body(null); // 400 si el tipo no es válido
            }

            Optional<Usuario> usuarioOpt = usuarioRepository.findById(String.valueOf(usuarioId));

            if (usuarioOpt.isEmpty()) {
                return ResponseEntity.badRequest().body(null); // 400 si el usuario no existe
            }

            Usuario usuario = usuarioOpt.get();

            // Crear el nuevo préstamo
            Prestamo nuevoPrestamo = new Prestamo();
            nuevoPrestamo.setEjemplar(ejemplar);
            nuevoPrestamo.setUsuario(usuario); // Asignar usuario
            nuevoPrestamo.setFechaInicio(LocalDate.parse((String) datos.get("fecha_inicio")));
            nuevoPrestamo.setFechaDevolucion(datos.get("fecha_devolucion") != null
                    ? LocalDate.parse((String) datos.get("fecha_devolucion"))
                    : null);

            // Actualizar estado del ejemplar
            ejemplar.setEstado("Prestado");
            ejemplarRepository.save(ejemplar);

            // Guardar el préstamo
            Prestamo prestamoGuardado = prestamoRepository.save(nuevoPrestamo);

            return ResponseEntity.status(HttpStatus.CREATED).body(prestamoGuardado); // 201 Created
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // 500 en caso de error inesperado
        }
    }





    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPrestamo(@PathVariable Long id) {
        // Buscar el préstamo por ID
        Optional<Prestamo> prestamoOpt = prestamoRepository.findById(id);

        if (prestamoOpt.isPresent()) {
            Prestamo prestamo = prestamoOpt.get();

            // Cambiar el estado del ejemplar asociado a "Disponible"
            Ejemplar ejemplar = prestamo.getEjemplar();
            if (ejemplar != null) {
                ejemplar.setEstado("Disponible");
                ejemplarRepository.save(ejemplar); // Actualizar el estado del ejemplar
            }

            // Eliminar el préstamo
            prestamoRepository.deleteById(id);

            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            // Si no se encuentra el préstamo, devolver un 404 Not Found
            return ResponseEntity.notFound().build();
        }
    }



}



