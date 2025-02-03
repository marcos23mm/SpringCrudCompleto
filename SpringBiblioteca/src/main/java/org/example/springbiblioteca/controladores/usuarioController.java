package org.example.springbiblioteca.controladores;

import org.example.springbiblioteca.modelo.Usuario;
import org.example.springbiblioteca.servicios.usuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuario")
public class usuarioController {

    private final usuarioService usuarioService;

    public usuarioController (usuarioService usuarioService){

        this.usuarioService = usuarioService;
    }

    //Obtener usuarios
    @GetMapping
    public List<Usuario> listar(){
        return usuarioService.obtenerTodos();
    }


    // Nuevo endpoint para buscar un usuario por DNI
    @GetMapping("/dni/{dni}")
    public ResponseEntity<Usuario> obtenerPorDni(@PathVariable String dni) {
        Optional<Usuario> usuario = usuarioService.obtenerUsuarioPorDni(dni);
        return usuario.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    //Crear usuario
    @PostMapping
    public ResponseEntity<Usuario> crear(@RequestBody Usuario usuario) {
        Usuario usuarioNuevo = usuarioService.guardar(usuario);
        return ResponseEntity.ok(usuarioNuevo);
    }

    //Actualizar usuario
    @PutMapping("/{dni}")
    public ResponseEntity<Usuario> actualizar(@PathVariable String dni, @RequestBody Usuario usuario) {
        // Buscar el usuario existente por DNI
        Optional<Usuario> usuarioOptional = usuarioService.obtenerUsuarioPorDni(dni);

        if (!usuarioOptional.isPresent()) {
            // Si no existe, se devuelve 404 Not Found
            return ResponseEntity.notFound().build();
        }

        // Se obtiene el usuario existente desde la base de datos
        Usuario usuarioExistente = usuarioOptional.get();

        // Actualizar los campos del usuario existente con los valores del objeto recibido
        // Se deja el DNI y el ID sin modificar para que se mantenga la identificación
        usuarioExistente.setNombre(usuario.getNombre());
        usuarioExistente.setEmail(usuario.getEmail());
        usuarioExistente.setPassword(usuario.getPassword());
        usuarioExistente.setTipo(usuario.getTipo());
        usuarioExistente.setPenalizacionHasta(usuario.getPenalizacionHasta());

        // Se guarda el usuario actualizado en la base de datos
        Usuario usuarioGuardado = usuarioService.guardar(usuarioExistente);

        // Se devuelve el usuario actualizado con una respuesta 200 OK
        return ResponseEntity.ok(usuarioGuardado);
    }


    @DeleteMapping("/{dni}")
    // Es string para cuando devuelve dato
    public ResponseEntity<String> eliminar(@PathVariable String dni) {
        if(!usuarioService.obtenerUsuarioPorDni(dni).isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
            //Lo de abajo responderia 200 OK en vez de 404 Not Found
            //return ResponseEntity.ok("Usuario no encontrado");
        }
        usuarioService.eliminar(dni);
        //Es por esta parte por la que necesito String en vez de usuario
        return ResponseEntity.ok("Usuario con DNI " + dni + " eliminado correctamente.");
    }





}
