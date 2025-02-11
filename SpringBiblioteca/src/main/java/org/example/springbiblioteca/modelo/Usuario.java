package org.example.springbiblioteca.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "El DNI es obligatorio")
    @Column(name = "dni", unique = true, length = 15, nullable = false)
    private String dni;

    @NotBlank(message = "El nombre es obligatorio")
    @Column(name = "nombre", length = 100, nullable = false)
    @Pattern(regexp = "^[a-zA-Z0-9\\s]+$", message = "El nombre solo puede contener caracteres alfanuméricos")
    private String nombre;

    @NotBlank(message = "El email es obligatorio")
    @Column(name = "email", unique = true, length = 100, nullable = false)
    @Email(message = "El correo electrónico debe ser válido y terminar en '@gmail.com'")
    @Pattern(regexp = "^[A-Za-z0-9]{1,50}@gmail\\.com$", message = "Solo se permiten correos de tipo Gmail")
    private String email;

    @NotBlank(message = "La contraseña es obligatoria")
    @Column(name = "password", length = 255, nullable = false)
    @Length(min = 8, max = 12, message = "La contraseña debe tener entre 8 y 12 caracteres")
    private String password;

    @NotBlank(message = "El tipo es obligatorio")
    @Column(name = "tipo", nullable = false)
     @Pattern(regexp = "^(normal|administrador)$", message = "El tipo solo puede ser 'normal' o 'administrador'")
    private String tipo;

    @Column(name = "penalizacion_hasta")
    private LocalDate penalizacionHasta;

    // Relación opcional con préstamos
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Prestamo> prestamos;

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

    public Set<Prestamo> getPrestamos() {
        return prestamos;
    }

    public void setPrestamos(Set<Prestamo> prestamos) {
        this.prestamos = prestamos;
    }
}
