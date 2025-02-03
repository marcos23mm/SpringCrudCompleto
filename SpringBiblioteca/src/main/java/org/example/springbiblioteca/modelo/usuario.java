package org.example.springbiblioteca.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity
@Table(name = "usuario")
public class usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "El DNI es obligatorio")
    @Column(name = "dni", unique = true, length = 15, nullable = false)
    private String dni;

    @NotBlank(message = "El nombre es obligatorio")
    @Column(name = "nombre", length = 100, nullable = false)
    private String nombre;

    @NotBlank(message = "El email es obligatorio")
    @Column(name = "email", unique = true, length = 100, nullable = false)
    private String email;

    @NotBlank(message = "La contraseña es obligatoria")
    @Column(name = "password", length = 255, nullable = false)
    private String password;

    @NotBlank(message = "El tipo es obligatorio")
    @Column(name = "tipo", nullable = false)
    private String tipo;

    @Column(name = "penalizacion_hasta")
    private LocalDate penalizacionHasta;

    // Getters y setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDate getPenalizacionHasta() {
        return penalizacionHasta;
    }

    public void setPenalizacionHasta(LocalDate penalizacionHasta) {
        this.penalizacionHasta = penalizacionHasta;
    }
}
