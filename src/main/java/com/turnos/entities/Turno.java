package com.turnos.entities;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "turnos")
public class Turno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //aqui id progresivo unico para cada turno
    @Column(unique = true, nullable = false)
    private String identificadorProgresivo;

    @Enumerated(EnumType.STRING)  //aqui guardamos como String en BD
    @Column(nullable = false)
    private TurnoEstado estado;  //aqui enum en lugar de String - FIXME RESUELTO

    //aqui fecha y hora de creacion del turno
    @Column(nullable = false)
    private LocalDateTime fecha;

    //aqui descripcion del tramite a realizar
    @Column(nullable = false, length = 500)
    private String descripcionTramite;

    //aqui relacion ManyToOne: muchos turnos -> un ciudadano
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ciudadano_id", nullable = false)
    private Ciudadano ciudadano;

    //aqui constructor vacio requerido por el JPA
    public Turno() {
    }

    //aqui constructor actualizado con enum en lugar de String
    public Turno(String identificadorProgresivo, TurnoEstado estado, LocalDateTime fecha, String descripcionTramite, Ciudadano ciudadano) {
        this.identificadorProgresivo = validarIdentificador(identificadorProgresivo);
        this.estado = validarEstado(estado);
        this.fecha = validarFecha(fecha);
        this.descripcionTramite = validarDescripcion(descripcionTramite);
        this.ciudadano = validarCiudadano(ciudadano);
    }

    //aqui metodos de validacion para asegurar datos correctos
    private String validarIdentificador(String identificador) {
        if (identificador == null || identificador.trim().isEmpty()) {
            throw new IllegalArgumentException("El identificador no puede ser nulo o vacío");
        }
        return identificador.trim();
    }

    private TurnoEstado validarEstado(TurnoEstado estado) {
        if (estado == null) {
            throw new IllegalArgumentException("El estado no puede ser nulo");
        }

        return estado;
    }

    private LocalDateTime validarFecha(LocalDateTime fecha){
        if (fecha == null){
            throw new IllegalArgumentException("La fecha no puede ser nula");
        }
        return fecha;
    }

    private String validarDescripcion(String descripcion) {
        if (descripcion == null || descripcion.trim().isEmpty()) {
            throw new IllegalArgumentException("La descripción del trámite no puede ser nula o vacía");
        }
        return descripcion.trim();
    }

    private Ciudadano validarCiudadano(Ciudadano ciudadano) {
        if (ciudadano == null) {
            throw new IllegalArgumentException("El ciudadano no puede ser nulo");
        }
        return ciudadano;
    }

    /// getters y setters ///
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getIdentificadorProgresivo() { return identificadorProgresivo; }
    public void setIdentificadorProgresivo(String identificadorProgresivo) {
        this.identificadorProgresivo = validarIdentificador(identificadorProgresivo);
    }

    public TurnoEstado getEstado() { return estado; }  //aqui retorna enum
    public void setEstado(TurnoEstado estado) { this.estado = validarEstado(estado); }  //aqui recibe enum

    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = validarFecha(fecha); }

    public String getDescripcionTramite() { return descripcionTramite; }
    public void setDescripcionTramite(String descripcionTramite) {
        this.descripcionTramite = validarDescripcion(descripcionTramite);
    }

    public Ciudadano getCiudadano() { return ciudadano; }
    public void setCiudadano(Ciudadano ciudadano) { this.ciudadano = validarCiudadano(ciudadano);}

    // metodos EXTRAS
    public boolean estaEnEspera() {
        //aqui verificamos si el estado actual del turno es "En espera"
        //usamos equals() y regresa true cuando el turno esta pendiente de ser atendido
        return TurnoEstado.EN_ESPERA.equals(estado);
    }

    public boolean estaAtendido() {
        //aqui verificamos si el estado actual del turno es "Ya atendido"
        //retorna true cuando el turno ya fue procesado/completado
        return TurnoEstado.ATENDIDO.equals(estado);
    }

    public void marcarComoAtendido() {
        //aqui cambiamos el estado del turno de "En espera" a "Ya atendido"
        //este metodo simula la finalizacion del tramite por el empleado
        //se usa cuando el empleado completa la atencion del ciudadano
        this.estado = TurnoEstado.ATENDIDO;
    }
}