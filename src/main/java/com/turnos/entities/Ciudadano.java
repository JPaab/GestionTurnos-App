package com.turnos.entities;
import jakarta.persistence.*;
import java.util.List;
/**
 *  * Entidad JPA que representa a un ciudadano en un sistema de gestion de turnos.
 *  * <p>
 *  * Esta clase mapea la tabla "ciudadanos" en la BD y contiene toda la información de cada ciudadano que pueda solicitar turnos.
 *  * <p>
 *  * Un ciudadano puede tener cero o multiplices turnos asignados (relacion one to many)
 *  */

@Entity
@Table(name = "ciudadanos") //aqui especifica el nombre de la tabla en la BD
public class Ciudadano {

    /// /////////////////////// ATRIBUTOS ////////////////////////
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String nombre;

    @Column(nullable = false, length = 20)
    private String apellido;

    @Column(nullable = false, length = 10, unique = true)
    private String DNI;

    @Column(nullable = false, length = 100, unique = true)
    private String email;

    @OneToMany(mappedBy = "ciudadanos", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Turno> turnos;

    //Aqui el constructor vacio requerido por el JPA.
    public Ciudadano() {
    }

    public Ciudadano(String nombre, String apellido, String DNI, String email) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.DNI = DNI;
        this.email = email;
    }
///  getters and setters ///
    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}

    public String getApellido() {return apellido;}
    public void setApellido(String apellido) {this.apellido = apellido;}

    public String getDNI() {return DNI;}
    public void setDNI(String DNI) {this.DNI = DNI;}

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    public List<Turno> getTurnos() {return turnos;}
    public void setTurnos(List<Turno> turnos) {this.turnos = turnos;}

}
