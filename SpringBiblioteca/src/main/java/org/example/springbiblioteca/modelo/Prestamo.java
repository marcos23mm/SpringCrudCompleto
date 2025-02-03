    package org.example.springbiblioteca.modelo;

    import com.fasterxml.jackson.annotation.JsonBackReference;
    import jakarta.persistence.*;
    import jakarta.validation.constraints.NotNull;
    import org.hibernate.annotations.OnDelete;
    import org.hibernate.annotations.OnDeleteAction;

    import java.time.LocalDate;

    @Entity
    @Table(name = "prestamo")
    public class Prestamo {
        @Id
        @Column(name = "id", nullable = false)
        private Integer id;

        @NotNull
        @ManyToOne(fetch = FetchType.LAZY, optional = false)
        @OnDelete(action = OnDeleteAction.CASCADE)
        @JoinColumn(name = "ejemplar_id", nullable = false)
        @JsonBackReference
        private Ejemplar ejemplar;

        @Column(name = "fecha_inicio")
        private LocalDate fechaInicio;

        @Column(name = "fecha_devolucion")
        private LocalDate fechaDevolucion;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Ejemplar getEjemplar() {
            return ejemplar;
        }

        public void setEjemplar(Ejemplar ejemplar) {
            this.ejemplar = ejemplar;
        }

        public LocalDate getFechaInicio() {
            return fechaInicio;
        }

        public void setFechaInicio(LocalDate fechaInicio) {
            this.fechaInicio = fechaInicio;
        }

        public LocalDate getFechaDevolucion() {
            return fechaDevolucion;
        }

        public void setFechaDevolucion(LocalDate fechaDevolucion) {
            this.fechaDevolucion = fechaDevolucion;
        }

    }