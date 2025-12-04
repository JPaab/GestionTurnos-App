package com.turnos.entities;
import jakarta.persistence.*;
import java.util.List;

///---Esto es entidad JPA que representa a un ciudadano en un sistema de gestion de turnos.
///---Esta clase mapea la tabla "ciudadanos" en la BD y contiene toda la información de cada ciudadano que pueda solicitar turnos.
///---Un ciudadano puede tener cero o multiplices turnos asignados (relacion one to many)

@Entity
@Table(name = "ciudadanos") //aqui especifica el nombre de la tabla en la BD
public class Ciudadano {

    /// /////////////////////// ATRIBUTOS ////////////////////////
    //aqui se definiras todos los atributos de la entidad con sus anotaciones JPA
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String nombre;

    @Column(nullable = false, length = 20)
    private String apellido;

    @Column(nullable = false, length = 10, unique = true)
    private String dni;

    @Column(nullable = false, length = 100, unique = true)
    private String email;

    @OneToMany(mappedBy = "ciudadano", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Turno> turnos;

    //aqui el constructor vacio requerido por el JPA
    // y luego los constructores para crear las instancias de Ciudadano
    public Ciudadano() {
    }

    public Ciudadano(String nombre, String apellido, String dni, String email) {
        //aqui constructores con sus validaciones basicas
        this.nombre = validarNombre(nombre);
        this.apellido = validarApellido(apellido);
        this.dni = validarDni(dni);
        this.email = validarEmail(email);
    }

    //aqui aplicamos metodos de validacion para asegurar datos correctos
    private String validarNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede ser nulo o vacío");
        }
        return nombre.trim();
    }

    private String validarApellido(String apellido) {
        if (apellido == null || apellido.trim().isEmpty()) {
            throw new IllegalArgumentException("El apellido no puede ser nulo o vacío");
        }
        return apellido.trim();
    }

    private String validarDni(String dni) {
        if (dni == null || dni.trim().isEmpty()) {
            throw new IllegalArgumentException("El DNI no puede ser nulo o vacío");
        }
        return dni.trim();
    }

    private String validarEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("El email no puede ser nulo o vacío");
        }
        String emailFormato = email.trim();
        if (!emailFormato.contains("@") || !emailFormato.contains(".")) {
            throw new IllegalArgumentException("Introduzca un formato valido (Debe contener '@' y '.')");
        }
        return emailFormato.toLowerCase();
    }

    //aqui estarian los getters y setters con sus encapsulamientos y validaciones
    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = validarNombre(nombre);}

    public String getApellido() {return apellido;}
    public void setApellido(String apellido) {this.apellido = validarApellido(apellido);}

    public String getDni() {return dni;}
    public void setDni(String dni) {this.dni = validarDni(dni);}

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = validarEmail(email);}

    public List<Turno> getTurnos() {return turnos;} //aqui este get retorna List del Collections
    public void setTurnos(List<Turno> turnos) {this.turnos = turnos;} //aqui setter recibe List

    //aqui implementare unos EXTRAS con manejo de Collections
    /// NOTA: %s es un placeholder, inserta cadenas de texto en un formato
    public String getNombreCompleto() {
        //aqui combina nombre y apellido usando String.format() ya que es mas eficiente y legible, viable para un clean code
        return String.format("%s %s", nombre, apellido);
    }

    public boolean tieneTurnos() {
        //aqui verifica si el ciudadano tiene turnos asignados
        if (turnos == null) return false;
        return !turnos.isEmpty();
    }

    public int getCantidadTurnos() {
        //aqui regresa la cantidad de turnos usando operador ternario
        return tieneTurnos() ? turnos.size() : 0;
    }
}
