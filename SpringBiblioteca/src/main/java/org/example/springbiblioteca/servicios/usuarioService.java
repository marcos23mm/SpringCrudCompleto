package org.example.springbiblioteca.servicios;


import jakarta.transaction.Transactional;
import org.example.springbiblioteca.modelo.Usuario;
import org.example.springbiblioteca.repositorios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class usuarioService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public usuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> obtenerTodos(){
        return usuarioRepository.findAll();
    }


    public Optional<Usuario> obtenerUsuarioPorDni(String dni){
        return usuarioRepository.findByDni(dni);
    }


    public Usuario guardar(Usuario usuario){
        return usuarioRepository.save(usuario);
    }


    //@Transactional actualemte esto no es necesario aque es muy potente


    public void eliminar(String dni){
        this.usuarioRepository.deleteByDni(dni);
        //usuarioRepository.deleteByDni(dni);
    }



}
