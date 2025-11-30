package com.turnos.entities;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "turnos")
public class Turno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //aqui seria un id progresivo unico para cada turno (Ej: T0001, T0002)
    @Column(unique = true, nullable = false)
    private String identificadorProgresivo;

    //aqui seria para ver el estado del turno ya sea EN ESPERA o YA ATENDIDO
    @Column(nullable = false)
    private String estado; //FIXME//////////////////////////////////////////////////////////////////////////////

    //aqui para ver la fecha y hora en la que se ha creado el turno
    @Column(nullable = false)
    private LocalDateTime fecha;

    //aqui seria la descripcion del tramite que se vaya a realizar
    @Column(nullable = false, length = 500)
    private String descripcionTramite;

    //aqui se usa la relacion ManyToOne ya que muchos turnos pueden pertenecer a un ciudadano
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ciudadano_id", nullable = false)
    private Ciudadano ciudadano;

    //aqui el constructor vacio requerido por el JPA
    public Turno() {
    }

    public Turno(String identificadorProgresivo, String estado, LocalDateTime fecha, String descripcionTramite, Ciudadano ciudadano) {
        this.identificadorProgresivo = identificadorProgresivo;
        this.estado = estado;
        this.fecha = fecha;
        this.descripcionTramite = descripcionTramite;
        this.ciudadano = ciudadano;
    }

    /// getter y setters ///
    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public String getIdentificadorProgresivo() {return identificadorProgresivo;}
    public void setIdentificadorProgresivo(String identificadorProgresivo) {this.identificadorProgresivo = identificadorProgresivo;}

    public String getEstado() {return estado;}
    public void setEstado(String estado) {this.estado = estado;}

    public LocalDateTime getFecha() {return fecha;}
    public void setFecha(LocalDateTime fecha) {this.fecha = fecha;}

    public String getDescripcionTramite() {return descripcionTramite;}
    public void setDescripcionTramite(String descripcionTramite) {this.descripcionTramite = descripcionTramite;}

    public Ciudadano getCiudadano() {return ciudadano;}
    public void setCiudadano(Ciudadano ciudadano) {this.ciudadano = ciudadano;}
}