package com.turnos.persistence;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

//aqui centralizo la configuracion de JPA para crear los EntityManager
public class ConfigJPA {

    //aqui creo un unico EntityManagerFactory para toda la aplicacion
    private static final EntityManagerFactory emf;

    static {
        try {
            //aqui cargo la unidad de persistencia definida en persistence.xml
            emf = Persistence.createEntityManagerFactory("TurnosPU");
        } catch (Throwable ex) {
            //aqui muestro el error si falla la configuracion de JPA
            System.err.println("ERROR al crear EntityManagerFactory: " + ex);
            //aqui lanza una excepcion si falla el initializer
            throw new ExceptionInInitializerError(ex);
        }

    }

    //aqui devuelvo un nuevo EntityManager para usar en los repositorios
    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public static void close() {
        //aqui cierro el EntityManagerFactory al apagar la aplicacion
        emf.close();
    }
}
