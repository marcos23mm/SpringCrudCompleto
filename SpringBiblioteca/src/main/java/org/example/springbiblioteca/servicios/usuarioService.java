package org.example.springbiblioteca.servicios;


import jakarta.transaction.Transactional;
import org.example.springbiblioteca.modelo.usuario;
import org.example.springbiblioteca.repositorios.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class usuarioService {

    private final UsuarioRepository usuarioRepository;

    public usuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<usuario> obtenerTodos(){
        return usuarioRepository.findAll();
    }

    public Optional<usuario> obtenerUsuarioPorDni(String dni){
        return usuarioRepository.findByDni(dni);
    }

    public usuario guardar(usuario usuario){
        return usuarioRepository.save(usuario);
    }
    ////////////////
    @Transactional
    public void eliminar(String dni){
        usuarioRepository.deleteByDni(dni);
    }

}
