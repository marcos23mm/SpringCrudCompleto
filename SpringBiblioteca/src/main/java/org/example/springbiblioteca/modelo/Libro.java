package org.example.springbiblioteca.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "libro")
public class Libro {

    @Id
    @NotNull(message = "ISBN no puede ser nulo")
    @Pattern(regexp = "^(978|979)-[0-9]{1,5}-[0-9]{1,7}-[0-9]{1,7}-[0-9]{1}$", message = "El formato del ISBN no es válido")
    private String isbn;

    @NotNull(message = "El título no puede ser nulo")
    @Size(max = 255, message = "El título no puede exceder los 255 caracteres")
    @Pattern(regexp = "^[a-zA-Z0-9\\sáéíóúÁÉÍÓÚñÑ]+$", message = "El título solo puede contener caracteres alfanuméricos y acentos")
    private String titulo;

    @NotNull(message = "El autor no puede ser nulo")
    @Size(max = 255, message = "El autor no puede exceder los 255 caracteres")
    @Pattern(regexp = "^[a-zA-Z0-9\\sáéíóúÁÉÍÓÚñÑ]+$", message = "El autor solo puede contener caracteres alfanuméricos y acentos")
    private String autor;

    // Getters y setters

    public String getIsbn() {
        return isbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getAutor() {
        return autor;
    }
    public void setAutor(String autor) {
        this.autor = autor;
    }
}
