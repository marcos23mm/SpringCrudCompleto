package org.example.springbiblioteca.controladores;

import org.example.springbiblioteca.modelo.Ejemplar;
import org.example.springbiblioteca.servicios.ejemplarService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ejemplar")

public class ejemplarController {

  private final ejemplarService ejemplarService;

  public ejemplarController(ejemplarService ejemplarService) {
      this.ejemplarService = ejemplarService;
  }

  // Obtener ejemplares
    @GetMapping
    public List<Ejemplar> obtenerTodosEjemplares(){
      return ejemplarService.obtenerTodosEjemplares();
    }


}
